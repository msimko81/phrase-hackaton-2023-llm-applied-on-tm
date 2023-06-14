package com.memsource.hackaton.llmappliedontm.domain.dataset;

import com.memsource.hackaton.llmappliedontm.domain.dataset.entity.Dataset;
import com.memsource.hackaton.llmappliedontm.infrastructure.database.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DatasetRepositoryTest {

    @Mock
    private Database database;

    @InjectMocks
    private DatasetRepository sut;

    @BeforeEach
    public void setUp() {
        when(database.getDatasets()).thenReturn(
                Map.of("dataset1", Dataset.builder().name("first dataset").build(),
                "dataset2", Dataset.builder().name("second dataset").build()));
    }

    @Test
    void findsJustThoseWithGivenId() {
        Dataset result = sut.findById("dataset1");

        assertEquals("first dataset", result.getName());
    }

    @Test
    void returnsNullIfDatasetWithGivenIdDoesNotExist() {
        Dataset result = sut.findById("non-existing");

        assertNull(result);
    }

    @Test
    void correctlyTransformsDatasetsToIdNamePairs() {
        Map<String, String> result = sut.getAllIdsWithNames();

        assertEquals(2, result.size());
        assertEquals("first dataset", result.get("dataset1"));
        assertEquals("second dataset", result.get("dataset2"));
    }

}