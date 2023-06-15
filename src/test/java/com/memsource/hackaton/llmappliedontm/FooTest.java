package com.memsource.hackaton.llmappliedontm;

import com.memsource.hackaton.llmappliedontm.domain.dataset.entity.Dataset;
import com.memsource.hackaton.llmappliedontm.infrastructure.openai.ChatbotService;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.model.Model;
import com.theokanning.openai.service.OpenAiService;
import com.memsource.hackaton.llmappliedontm.infrastructure.openai.OpenAiProxy;
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
    OpenAiProxy openAiProxy;
    @Autowired
    private ChatbotService chatbotService;

    @Test
    void bar() {
        String prompt = """
                Rewrite the following in a gender neutral way. The text contains English sentence followed by Slovak translation (divided by the pipe). Each such block starts with a new line and with a dash. Rewrite both parts using the respective language.

                - He is a doctor.|On je lekár.
                - She is an actress.|Je herečka.
                - His mother and father lives abroad.|Jeho matka a otec žijú v zahraničí.
                                        """;

        System.out.println(openAiProxy.requestCompletion(prompt));
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
