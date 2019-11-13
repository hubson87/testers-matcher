package com.project.hubert.testersmatcher.representation;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DictionaryItemRepresentation {
    public final Long id;
    public final String code;
}
