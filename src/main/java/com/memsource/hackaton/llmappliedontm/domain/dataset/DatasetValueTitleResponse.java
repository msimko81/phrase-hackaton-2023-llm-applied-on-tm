package com.memsource.hackaton.llmappliedontm.domain.dataset;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DatasetValueTitleResponse {

    String value;

    String title;

}
