package com.memsource.hackaton.llmappliedontm.domain.dataset;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DatasetIdNameResponse {

    String id;

    String name;

}
