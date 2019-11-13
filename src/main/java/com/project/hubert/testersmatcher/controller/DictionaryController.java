package com.project.hubert.testersmatcher.controller;

import com.project.hubert.testersmatcher.representation.DictionariesRepresentation;
import com.project.hubert.testersmatcher.representation.mapper.DictionaryRepresentationMapper;
import com.project.hubert.testersmatcher.services.DictionaryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/dictionary")
public class DictionaryController {
    private DictionaryService dictionaryService;

    public DictionaryController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @GetMapping(path = "/all")
    public DictionariesRepresentation getAllDictionaries() {
        return DictionaryRepresentationMapper.fromDictionaries(dictionaryService.getCountries(), dictionaryService.getDevices());
    }
}
