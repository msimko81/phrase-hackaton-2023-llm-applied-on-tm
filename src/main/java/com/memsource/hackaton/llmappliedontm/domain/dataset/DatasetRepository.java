package com.memsource.hackaton.llmappliedontm.domain.dataset;

import com.memsource.hackaton.llmappliedontm.domain.dataset.entity.Dataset;
import com.memsource.hackaton.llmappliedontm.infrastructure.database.Database;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class DatasetRepository {

    private final Database database;

    public Dataset findById(String id) {
        return database.getDatasets().get(id);
    }

    public Map<String, String> getAllIdsWithNames() {
        Map<String, String> result = new HashMap<>();
        database.getDatasets().forEach((key, value) -> result.put(key, value.getName()));

        return result;
    }

}
