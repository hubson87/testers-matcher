package com.project.hubert.testersmatcher.domain.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DictionaryItem {
    private final String code;
    private final String name;
}
