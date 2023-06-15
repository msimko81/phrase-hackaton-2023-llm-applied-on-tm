package com.memsource.hackaton.llmappliedontm;

import com.memsource.hackaton.llmappliedontm.domain.dataset.entity.Dataset;
import com.memsource.hackaton.llmappliedontm.infrastructure.openai.ChatbotService;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.model.Model;
import com.theokanning.openai.service.OpenAiService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@Slf4j
//@Disabled
public class FooTest {

    @Autowired
    OpenAiService openAiService;

    @Autowired
    private ChatbotService chatbotService;

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

    @Test
    void prompts() {
        Dataset vaporiseDataset =
                chatbotService.vaporiseDataset("Rewrite the following in a gender neutral way.", "dataset1",
                        "text-davinci-002",
                        1);
        Dataset vaporiseDataset2 =
                chatbotService.vaporiseDataset("Rewrite the following in a gender neutral way.", "dataset1",
                        "text-davinci-002",
                        2);
        Dataset vaporiseDataset3 =
                chatbotService.vaporiseDataset("Rewrite the following in a gender neutral way.", "dataset1",
                        "text-davinci-002",
                        3);
        printSegments(vaporiseDataset);
        printSegments(vaporiseDataset2);
        printSegments(vaporiseDataset3);
    }

    private void printSegments(Dataset dataset) {
        System.out.println(dataset.getSegments()
                .stream()
                .map(s -> s.getSource() + " -> " + s.getTarget())
                .collect(Collectors.joining("\n")));
        System.out.println("====================================");
    }
}
