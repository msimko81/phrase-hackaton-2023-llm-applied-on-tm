package com.memsource.hackaton.llmappliedontm;

import com.memsource.hackaton.llmappliedontm.infrastructure.openai.OpenAiProxy;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
@Disabled
public class FooTest {

    @Autowired
    OpenAiProxy openAiProxy;

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
}
