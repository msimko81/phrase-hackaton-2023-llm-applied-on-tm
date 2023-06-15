package com.memsource.hackaton.llmappliedontm.infrastructure.openai;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("open-ai")
@Data
public class ChatbotServiceConfig {

    private String model;
    private Integer maxTokens;
    private boolean echo = false;
}
