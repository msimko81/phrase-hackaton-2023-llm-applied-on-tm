package com.memsource.hackaton.llmappliedontm.infrastructure.openai;

import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OpenAiProxy {
    private final OpenAiService openAiService;
    private final ChatbotServiceConfig config;

    public String requestCompletion(String prompt) {
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt(prompt)
                .model(config.getModel())
                .echo(config.isEcho())
                .maxTokens(config.getMaxTokens())
                .build();

        return openAiService.createCompletion(completionRequest).getChoices().stream().findFirst()
                .map(CompletionChoice::getText).orElseThrow();
    }
}
