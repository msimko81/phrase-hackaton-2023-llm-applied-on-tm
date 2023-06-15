package com.memsource.hackaton.llmappliedontm.openai;

import com.memsource.hackaton.llmappliedontm.infrastructure.openai.ChatbotServiceConfig;
import com.memsource.hackaton.llmappliedontm.infrastructure.openai.OpenAiClient;
import com.theokanning.openai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/openai")
@RequiredArgsConstructor
class OpenAiController {

    private final OpenAiClient openAiClient;

    private final OpenAiService openAiService;

    @GetMapping("list-supported-models")
    List<String> listSupportedModels() {
        return openAiClient.listAvailableModels();
    }

    @PostMapping
    String prompt(@RequestParam(name = "model", defaultValue = "text-davinci-002") String model,
            @RequestParam(name = "maxTokens", defaultValue = "1024") Integer maxTokens,
            @RequestParam(name = "echo", defaultValue = "false") boolean echo,
            @RequestBody String prompt) {

        String response = new OpenAiClient(openAiService, ChatbotServiceConfig.builder()
                .maxTokens(maxTokens)
                .echo(echo)
                .build()).callChatbot(prompt, model);

        System.out.println(response);

        return response;
    }
}
