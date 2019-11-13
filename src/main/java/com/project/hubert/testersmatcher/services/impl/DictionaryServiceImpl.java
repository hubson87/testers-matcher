package com.project.hubert.testersmatcher.services.impl;

import com.project.hubert.testersmatcher.domain.model.DictionaryItem;
import com.project.hubert.testersmatcher.domain.repository.CountriesRepository;
import com.project.hubert.testersmatcher.domain.repository.DevicesRepository;
import com.project.hubert.testersmatcher.mapper.DictionaryMapper;
import com.project.hubert.testersmatcher.services.DictionaryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DictionaryServiceImpl implements DictionaryService {
    private CountriesRepository countriesRepository;
    private DevicesRepository devicesRepository;

    public DictionaryServiceImpl(CountriesRepository countriesRepository, DevicesRepository devicesRepository) {
        this.countriesRepository = countriesRepository;
        this.devicesRepository = devicesRepository;
    }

    @Override
    public List<DictionaryItem> getCountries() {
        return countriesRepository.findAll().stream().map(DictionaryMapper::fromCountry).collect(Collectors.toList());
    }

    @Override
    public List<DictionaryItem> getDevices() {
        return devicesRepository.findAll().stream().map(DictionaryMapper::fromDevice).collect(Collectors.toList());
    }
}
