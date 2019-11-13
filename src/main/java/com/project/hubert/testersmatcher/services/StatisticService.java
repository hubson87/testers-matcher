package com.project.hubert.testersmatcher.services;

import com.project.hubert.testersmatcher.domain.model.TesterSummaryAccumulator;

import java.util.List;

public interface StatisticService {
    List<TesterSummaryAccumulator> getStatistics(List<Long> countryIds, List<Long> deviceIds);
}
