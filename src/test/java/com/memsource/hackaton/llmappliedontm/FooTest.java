package com.memsource.hackaton.llmappliedontm;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.model.Model;
import com.theokanning.openai.service.OpenAiService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class FooTest {

    @Autowired
    OpenAiService openAiService;

    @Test
    void bar() {
        List<Model> models = openAiService.listModels();
        log.info("{}", models);

        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt("Replace miro by petr in the following text: hello miro")
                .model("davinci")
                .echo(true)
                .build();
        openAiService.createCompletion(completionRequest).getChoices().forEach(System.out::println);
    }
}
