package com.project.hubert.testersmatcher.representation;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DictionaryItemRepresentation {
    public final String code;
    public final String name;
}
