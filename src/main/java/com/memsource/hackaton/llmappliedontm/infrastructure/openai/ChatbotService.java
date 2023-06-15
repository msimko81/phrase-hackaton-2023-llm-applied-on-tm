package com.memsource.hackaton.llmappliedontm.infrastructure.openai;


import com.memsource.hackaton.llmappliedontm.domain.dataset.DatasetRepository;
import com.memsource.hackaton.llmappliedontm.domain.dataset.entity.Dataset;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.memsource.hackaton.llmappliedontm.domain.dataset.entity.Dataset.Segment;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatbotService {

    private final DatasetRepository datasetRepository;
    private final OpenAiClient openAiClient;

    public Dataset vaporiseDataset(String promptType, String datasetId, String model,
            int promptFormatNumber) {
        Dataset dataset = datasetRepository.findById(datasetId);
        String finalPrompt = formatPrompt(promptType, dataset, promptFormatNumber);

        String result = openAiClient.callChatbot(finalPrompt, model);
        List<Segment> segments = createSegmentsFromResponse(result);
        return dataset.toBuilder()
                .segments(segments)
                .build();
    }

    private List<Segment> createSegmentsFromResponse(String response) {
        String[] sentences = response.split("\n");
        if (sentences.length == 0) {
            log.warn("No sentences found in response");
            return Collections.emptyList();
        }

        List<Segment> segments = new ArrayList<>();
        for (String sentence : sentences) {
            String[] parts = sentence.split("\\|");
            if (parts.length != 2) {
                log.warn("Invalid sentence: {}", sentence);
                continue;
            }
            Segment segment = Segment.builder()
                    .source(parts[0])
                    .target(parts[1])
                    .build();
            segments.add(segment);
        }
        return segments;
    }

    private String formatPrompt(String promptType, Dataset dataset, int promptFormatNumber) {
        String promptFormat = getPromptFormat(promptFormatNumber);
        String segments = formatSegments(dataset, promptFormatNumber);

        String sourceLanguage = getLanguageLongForm(dataset.getSourceLanguage());
        String targetLanguage = getLanguageLongForm(dataset.getTargetLanguage());
        return String.format(promptFormat, promptType, sourceLanguage, targetLanguage, segments);
    }

    private String getPromptFormat(int number) {
        return switch (number) {
            case 1 -> """
                    %s The text contains %s sentence followed by %s translation.
                    Each such block is divided by a new line. Rewrite both parts using the respective language.
                                        
                    %s
                    """;
            case 2 -> """
                    %s The text contains %s sentence followed by %s translation (divided by the pipe). Each such block starts with a new line with a dash. Rewrite both parts using the respective language.
                    %s
                    """;
            default -> """
                    %s The following text contains Original %s sentence and %s translation.
                    The original sentence and the translation are separated by the pipe. Each block of text is separated by a new line.
                    The sentence should be rewritten in the same language as the original and its meaning should be preserved.
                                        
                    %s
                    """;
        };
    }

    private static String formatSegments(Dataset dataset, int promptFormatNumber) {
        return dataset.getSegments()
                .stream()
                .map(segment -> String.format(getSegmentsFormat(promptFormatNumber), segment.getSource(), segment.getTarget()))
                .collect(Collectors.joining("\n"));
    }

    private static String getSegmentsFormat(int promptFormatNumber) {
        return switch (promptFormatNumber) {
            case 2 -> "- %s | %s";
            default -> "%s | %s";
        };
    }

    private String getLanguageLongForm(String languageShort) {
        return switch (languageShort) {
            case "en" -> "English";
            case "de" -> "German";
            case "sk" -> "Slovak";
            case "pl" -> "Polish";
            case "cs" -> "Czech";
            default -> throw new IllegalArgumentException("Unknown language: " + languageShort);
        };
    }
}
