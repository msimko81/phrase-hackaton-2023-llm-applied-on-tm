package com.memsource.hackaton.llmappliedontm.domain.dataset.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class Dataset {

    private String id;

    private String name;

    private String sourceLanguage;

    private String targetLanguage;

    private List<Segment> segments;

    @Builder(access = AccessLevel.PUBLIC)
    @Data
    public static final class Segment {

        private String source;

        private String target;

    }

}
