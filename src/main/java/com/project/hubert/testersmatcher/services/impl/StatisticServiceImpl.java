package com.project.hubert.testersmatcher.services.impl;

import com.project.hubert.testersmatcher.domain.model.TesterSummaryAccumulator;
import com.project.hubert.testersmatcher.domain.repository.BugsRepository;
import com.project.hubert.testersmatcher.services.StatisticService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class StatisticServiceImpl implements StatisticService {
    private BugsRepository bugsRepository;

    public StatisticServiceImpl(BugsRepository bugsRepository) {
        this.bugsRepository = bugsRepository;
    }

    @Override
    public List<TesterSummaryAccumulator> getStatistics(List<Long> countryIds, List<Long> deviceIds) {
        List<Long> countries = CollectionUtils.isEmpty(countryIds) ? null : countryIds;
        List<Long> devices = CollectionUtils.isEmpty(deviceIds) ? null : deviceIds;

        return bugsRepository.findBugsByTesterCountryIdsAndDeviceIds(countries, devices);
    }
}
