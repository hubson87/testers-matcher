package com.project.hubert.testersmatcher.controller;

import com.project.hubert.testersmatcher.representation.StatisticsRepresentation;
import com.project.hubert.testersmatcher.representation.mapper.StatisticsRepresentationMapper;
import com.project.hubert.testersmatcher.services.StatisticService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/statistics")
public class StatisticsController {

    private StatisticService statisticService;

    public StatisticsController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping
    public List<StatisticsRepresentation> getStatistics(@RequestParam(value = "countryCodes", required = false) List<String> countries,
                                                        @RequestParam(value = "deviceNames", required = false) List<String> devices) {
        return StatisticsRepresentationMapper.fromAccumulators(statisticService.getStatistics(countries, devices));
    }
}
