package com.project.hubert.testersmatcher.representation;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@EqualsAndHashCode
@ToString
public class DictionaryItemRepresentation {
    public final String code;
    public final String name;
}
