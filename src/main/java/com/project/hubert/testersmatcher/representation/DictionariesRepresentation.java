package com.project.hubert.testersmatcher.representation;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class DictionariesRepresentation {
    private final List<DictionaryItemRepresentation> countries;
    private final List<DictionaryItemRepresentation> devices;
}
