package com.memsource.hackaton.llmappliedontm.domain.dataset;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DatasetServiceTest {

    @Mock
    private DatasetRepository datasetRepository;

    @InjectMocks
    private DatasetService sut;

    @Test
    void returnsValueAndTitleObjectForEveryDataset() {
        when(datasetRepository.getAllIdsWithNames()).thenReturn(Map.of("dataset1", "dataset name"));

        List<DatasetIdNameResponse> result = sut.getAllDatasetIdsAndNames();

        assertNotNull(result);
        assertEquals(1, result.size());
        DatasetIdNameResponse firstResponse = result.get(0);
        assertEquals("dataset1", firstResponse.getId());
        assertEquals("dataset name", firstResponse.getName());
    }

}