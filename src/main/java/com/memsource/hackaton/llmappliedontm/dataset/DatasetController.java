package com.memsource.hackaton.llmappliedontm.dataset;

import com.memsource.hackaton.llmappliedontm.dataset.request.VaporiseRequest;
import com.memsource.hackaton.llmappliedontm.domain.dataset.DatasetService;
import com.memsource.hackaton.llmappliedontm.domain.dataset.DatasetValueTitleResponse;
import com.memsource.hackaton.llmappliedontm.domain.dataset.entity.Dataset;
import com.memsource.hackaton.llmappliedontm.infrastructure.openai.ChatbotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/datasets")
@RequiredArgsConstructor
class DatasetController {

    private final ChatbotService chatbotService;

    private final DatasetService datasetService;

    @GetMapping
    List<DatasetValueTitleResponse> getDatasets() {
        return datasetService.getAllDatasetIdsAndNames();
    }

    @GetMapping("/{id}")
    Dataset getDataset(@PathVariable("id") String id) {
        return datasetService.getDataset(id);
    }

    @PostMapping("/vaporise")
    Dataset vaporiseDataset(@RequestBody VaporiseRequest request,
            @RequestParam(value = "model", required = false, defaultValue = "text-davinci-003") String model,
            @RequestParam(value = "promptFormatNumber", required = false, defaultValue = "1") int promptFormatNumber) {
        return chatbotService.vaporiseDataset(request.getPrompt(), request.getDatasetId(), model, promptFormatNumber);
    }
}
