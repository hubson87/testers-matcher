package com.project.hubert.testersmatcher.services;

import com.project.hubert.testersmatcher.domain.model.DictionaryItem;

import java.util.List;

public interface DictionaryService {
    List<DictionaryItem> getCountries();
    List<DictionaryItem> getDevices();
}
