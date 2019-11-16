package com.project.hubert.testersmatcher.representation.mapper;

import com.project.hubert.testersmatcher.domain.model.DictionaryItem;
import com.project.hubert.testersmatcher.representation.DictionariesRepresentation;
import com.project.hubert.testersmatcher.representation.DictionaryItemRepresentation;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DictionaryRepresentationMapper {
    private static List<DictionaryItemRepresentation> fromDictionary(List<DictionaryItem> dictionaryItems) {
        if (CollectionUtils.isEmpty(dictionaryItems)) {
            return new ArrayList<>();
        }
        return dictionaryItems.stream()
                .map(di -> DictionaryItemRepresentation.builder()
                        .name(di.getName())
                        .code(di.getCode())
                        .build())
                .collect(Collectors.toList());
    }

    public static DictionariesRepresentation fromDictionaries(List<DictionaryItem> countries, List<DictionaryItem> devices) {
        return DictionariesRepresentation.builder()
                .countries(fromDictionary(countries))
                .devices(fromDictionary(devices))
                .build();
    }
}
