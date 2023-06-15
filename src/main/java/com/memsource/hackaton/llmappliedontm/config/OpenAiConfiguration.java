package com.memsource.hackaton.llmappliedontm.config;

import com.theokanning.openai.service.OpenAiService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class OpenAiConfiguration {

    @Bean
    public OpenAiService openAiService(OpenAiConfigurationProperties config) {
        return new OpenAiService(config.getApiKey(), Duration.ofMinutes(2));
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @Configuration
    @ConfigurationProperties(prefix = "open-ai")
    public static class OpenAiConfigurationProperties {
        private String apiKey;
    }
}
