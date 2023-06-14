package com.memsource.hackaton.llmappliedontm.dataset;

import com.memsource.hackaton.llmappliedontm.domain.dataset.DatasetService;
import com.memsource.hackaton.llmappliedontm.domain.dataset.entity.Dataset;
import com.memsource.hackaton.llmappliedontm.infrastructure.openai.ChatbotService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DatasetController.class)
class DatasetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChatbotService chatbotService;

    @MockBean
    private DatasetService datasetService;

    @Test
    void returnsIdNamePairsForDatasetOverview() throws Exception {
        when(datasetService.getAllDatasetIdsAndNames()).thenReturn(
                Map.of("dataset1", "best dataset", "dataset2", "worst dataset")
        );

        mockMvc.perform(get("/api/v1/datasets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$.dataset1").value("best dataset"))
                .andExpect(jsonPath("$.dataset2").value("worst dataset"));
    }

    @Test
    void returnsDatasetDetails() throws Exception {
        when(datasetService.getDataset("datasetId")).thenReturn(Dataset.builder()
                .name("just another dataset")
                .build());

        mockMvc.perform(get("/api/v1/datasets/datasetId"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("just another dataset"));
    }

    @Test
    void returnsVaporisedDatasetAfterApplyingPrompt() throws Exception {
        when(chatbotService.vaporiseDataset(any(), any())).thenReturn(
                Dataset.builder()
                        .name("vaporised dataset")
                        .segments(
                                List.of(
                                        Dataset.Segment.builder().build()
                                )
                        )
                        .build()
        );

        mockMvc.perform(post("/api/v1/datasets/vaporise")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"datasetId\": \"idOfDataset\", \"prompt\": \"do it fast\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("vaporised dataset"))
                .andExpect(jsonPath("$.segments.length()").value(1));

    }

}