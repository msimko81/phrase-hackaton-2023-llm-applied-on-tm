package com.memsource.hackaton.llmappliedontm.infrastructure.openai;


import com.memsource.hackaton.llmappliedontm.domain.dataset.entity.Dataset;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatbotService {

    public Dataset vaporiseDataset(String prompt, String datasetId) {
        return null;
    }

}
