package com.memsource.hackaton.llmappliedontm.test;

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
    String getDataset(@RequestParam("model") String model, @RequestBody String prompt) {
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt(prompt)
                .model(model)
                .echo(true)
                .build();
        return openAiService.createCompletion(completionRequest).getChoices().stream().findFirst()
                .map(CompletionChoice::getText).orElseThrow();
    }
}
