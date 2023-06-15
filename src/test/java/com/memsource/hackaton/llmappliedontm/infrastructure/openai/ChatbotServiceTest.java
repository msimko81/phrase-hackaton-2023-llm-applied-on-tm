package com.memsource.hackaton.llmappliedontm.infrastructure.openai;

import com.memsource.hackaton.llmappliedontm.domain.dataset.DatasetRepository;
import com.memsource.hackaton.llmappliedontm.domain.dataset.entity.Dataset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ChatbotServiceTest {

    @Mock
    private DatasetRepository datasetRepository;
    @Mock
    private OpenAiClient openAiClient;
    private ChatbotService chatbotService;

    @BeforeEach
    void setUp() {
        chatbotService = new ChatbotService(datasetRepository, openAiClient);
    }

    @Test
    void vaporiseDataset_verifyCalls() {
        Dataset dataset = Dataset.builder()
                .name("dataset")
                .id("datasetId")
                .targetLanguage("en")
                .sourceLanguage("cs")
                .segments(List.of(Dataset.Segment.builder()
                        .source("source")
                        .target("target")
                        .build()))
                .build();
        doReturn(dataset).when(datasetRepository).findById("datasetId");
        doReturn("new sentence for source|new sentence for target").when(openAiClient).callChatbot(any(), "text-davinci-003");

        Dataset vaporiseDataset = chatbotService.vaporiseDataset("prompt", "datasetId", "text-davinci-003",
                0);

        assertThat(vaporiseDataset.getName()).isEqualTo(dataset.getName());
        assertThat(vaporiseDataset.getId()).isEqualTo(dataset.getId());
        assertThat(vaporiseDataset.getSourceLanguage()).isEqualTo(dataset.getSourceLanguage());
        assertThat(vaporiseDataset.getTargetLanguage()).isEqualTo(dataset.getTargetLanguage());

        assertThat(vaporiseDataset.getSegments()).hasSize(1);
        assertThat(vaporiseDataset.getSegments().get(0).getSource()).isEqualTo("new sentence for source");
        assertThat(vaporiseDataset.getSegments().get(0).getTarget()).isEqualTo("new sentence for target");

        verify(datasetRepository).findById("datasetId");
        ArgumentCaptor<String> promptCaptor = ArgumentCaptor.forClass(String.class);
        verify(openAiClient).callChatbot(promptCaptor.capture(), any());
        assertThat(promptCaptor.getValue())
                .contains("prompt.")
                .contains("Original Czech sentence and English translation")
                .contains("source|target")
        ;
    }
}