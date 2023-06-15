package com.memsource.hackaton.llmappliedontm.infrastructure.openai;

import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.model.Model;
import com.theokanning.openai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class OpenAiClient {

    private final OpenAiService openAiService;
    private final ChatbotServiceConfig config;

    public List<String> listAvailableModels() {
        return openAiService.listModels().stream().map(Model::getId).sorted().toList();
    }

    public String callChatbot(String prompt, String model) {
        log.info("Calling OpenAI with prompt: {}", prompt);
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt(prompt)
                .model(model)
                .echo(config.isEcho())
                .maxTokens(config.getMaxTokens())
                .frequencyPenalty(0.0)
                .presencePenalty(0.0)
                .bestOf(1)
                .topP(1.0)
                .temperature(1.0)
                .build();

        List<CompletionChoice> choices = openAiService.createCompletion(completionRequest).getChoices();
        log.info("number of choices: {}", choices.size());
        log.info("finish reason of the first choice: {}",
                choices.stream().findFirst().map(CompletionChoice::getFinish_reason).orElseThrow());

        String response = choices.stream().findFirst()
                .map(CompletionChoice::getText).orElseThrow();

        log.info("OpenAI response: {}", response);
        return response;
    }
}
