package com.project.hubert.testersmatcher.representation;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@ToString
public class DictionariesRepresentation {
    private final List<DictionaryItemRepresentation> countries;
    private final List<DictionaryItemRepresentation> devices;
}
