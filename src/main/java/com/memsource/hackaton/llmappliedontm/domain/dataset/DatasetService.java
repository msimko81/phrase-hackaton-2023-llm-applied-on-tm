package com.memsource.hackaton.llmappliedontm.domain.dataset;

import com.memsource.hackaton.llmappliedontm.domain.dataset.entity.Dataset;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DatasetService {

    private final DatasetRepository datasetRepository;

    public Dataset getDataset(String id) {
        return datasetRepository.findById(id);
    }

    public List<DatasetIdNameResponse> getAllDatasetIdsAndNames() {
        return datasetRepository.getAllIdsWithNames()
                .entrySet()
                .stream()
                .map(entry -> DatasetIdNameResponse.builder()
                        .id(entry.getKey())
                        .name(entry.getValue())
                        .build())
                .toList();
    }

}
