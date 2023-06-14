package com.memsource.hackaton.llmappliedontm.infrastructure.openai;

import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OpenAiClient {

    private final OpenAiService openAiService;

    public String callChatbot(String prompt, String model) {
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt(prompt)
                .model(model)
                .echo(false)
                .maxTokens(500)
                .build();
        return openAiService.createCompletion(completionRequest).getChoices().stream().findFirst()
                .map(CompletionChoice::getText).orElseThrow();
    }
}
