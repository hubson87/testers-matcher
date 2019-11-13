package com.project.hubert.testersmatcher.representation.mapper;

import com.project.hubert.testersmatcher.domain.model.TesterSummaryAccumulator;
import com.project.hubert.testersmatcher.exceptions.NotFoundException;
import com.project.hubert.testersmatcher.representation.StatisticsRepresentation;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class StatisticsRepresentationMapper {
    public static List<StatisticsRepresentation> fromAccumulators(List<TesterSummaryAccumulator> accumulators) {
        if (CollectionUtils.isEmpty(accumulators)) {
            throw new NotFoundException();
        }
        List<StatisticsRepresentation> res = new ArrayList<>();
        accumulators.forEach(acc -> res.add(StatisticsRepresentation.builder()
                .bugsCount(acc.getBugsCount())
                .country(acc.getCountry())
                .testerFirstName(acc.getTesterFirstName())
                .testerLastName(acc.getTesterLastName())
                .devicesCount(acc.getDevicesCount())
                .build()));
        return res;
    }
}
