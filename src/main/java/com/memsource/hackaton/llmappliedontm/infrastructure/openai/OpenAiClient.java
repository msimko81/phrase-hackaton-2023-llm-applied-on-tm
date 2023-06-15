package com.memsource.hackaton.llmappliedontm.infrastructure.openai;

import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class OpenAiClient {

    private final OpenAiService openAiService;

    public String callChatbot(String prompt, String model) {
        log.info("Calling OpenAI with prompt: {}", prompt);
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt(prompt)
                .model(model)
                .echo(false)
                .maxTokens(1216)
                .frequencyPenalty(0.0)
                .presencePenalty(0.0)
                .bestOf(1)
                .topP(1.0)
                .temperature(1.0)
                .build();
        String response = openAiService.createCompletion(completionRequest).getChoices().stream().findFirst()
                .map(CompletionChoice::getText).orElseThrow();
        log.info("OpenAI response: {}", response);
        return response;
    }
}
