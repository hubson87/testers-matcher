package com.project.hubert.testersmatcher.services.impl;

import com.project.hubert.testersmatcher.domain.model.TesterSummaryAccumulator;
import com.project.hubert.testersmatcher.domain.model.entity.Tester;
import com.project.hubert.testersmatcher.domain.repository.BugsRepository;
import com.project.hubert.testersmatcher.domain.repository.TestersRepository;
import com.project.hubert.testersmatcher.services.StatisticService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatisticServiceImpl implements StatisticService {
    private BugsRepository bugsRepository;
    private TestersRepository testersRepository;

    public StatisticServiceImpl(BugsRepository bugsRepository, TestersRepository testersRepository) {
        this.bugsRepository = bugsRepository;
        this.testersRepository = testersRepository;
    }

    @Override
    public List<TesterSummaryAccumulator> getStatistics(List<String> countryCodes, List<String> deviceNames) {
        List<String> countries = CollectionUtils.isEmpty(countryCodes) ? null : countryCodes;
        List<String> devices = CollectionUtils.isEmpty(deviceNames) ? null : deviceNames;

        List<TesterSummaryAccumulator> res = bugsRepository.findBugsByTesterCountryIdsAndDeviceIds(countries, devices);
        List<Long> foundTesters = CollectionUtils.isEmpty(res) ? null :
                res.stream().map(TesterSummaryAccumulator::getTesterId).collect(Collectors.toList());
        //add testers that has no matches in result, but fulfill the countries
        List<Tester> testersForCountries = testersRepository.getByCountryCodesAndExclude(countryCodes, foundTesters);
        if (testersForCountries != null) {
            testersForCountries.forEach(tester -> {
                TesterSummaryAccumulator acc = new TesterSummaryAccumulator(tester.getTesterId(), tester.getFirstName(),
                        tester.getLastName(), tester.getCountry().getCountryCode(), 0L, 0L);
                res.add(acc);
            });
        }
        return res;
    }
}
