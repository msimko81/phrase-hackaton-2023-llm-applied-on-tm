package com.memsource.hackaton.llmappliedontm.test;

import com.memsource.hackaton.llmappliedontm.infrastructure.openai.ChatbotServiceConfig;
import com.memsource.hackaton.llmappliedontm.infrastructure.openai.OpenAiProxy;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/openai-test")
@RequiredArgsConstructor
class OpenApiTestController {

    private final OpenAiService openAiService;

    @PostMapping
    String getDataset(@RequestParam(name = "model", defaultValue = "text-davinci-003") String model,
            @RequestParam(name = "maxTokens", defaultValue = "1024") Integer maxTokens,
            @RequestParam(name = "echo", defaultValue = "false") boolean echo,
            @RequestBody String prompt) {

        ChatbotServiceConfig config = ChatbotServiceConfig.builder()
                .model(model)
                .maxTokens(maxTokens)
                .echo(echo)
                .build();
        OpenAiProxy openAiProxy = new OpenAiProxy(openAiService, config);

        return openAiProxy.requestCompletion(prompt);
    }
}
