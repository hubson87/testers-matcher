package com.project.hubert.testersmatcher.representation.mapper;

import com.project.hubert.testersmatcher.domain.model.TesterSummaryAccumulator;
import com.project.hubert.testersmatcher.representation.StatisticsRepresentation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class StatisticsRepresentationMapperTest {

    @Test
    public void shouldMapEmptyAccumulatorTest() {
        assertThat(StatisticsRepresentationMapper.fromAccumulators(null)).isEmpty();
    }

    @Test
    public void shouldMapAccumulatorTest() {
        //given
        TesterSummaryAccumulator accumulator = new TesterSummaryAccumulator(1L, "name", "surname", "PL", 1L, 2L);

        //when
        List<StatisticsRepresentation> res = StatisticsRepresentationMapper.fromAccumulators(Collections.singletonList(accumulator));

        //then
        assertThat(res).isNotEmpty();
        assertThat(res.get(0).getTesterFirstName()).isEqualTo("name");
        assertThat(res.get(0).getTesterLastName()).isEqualTo("surname");
        assertThat(res.get(0).getCountry()).isEqualTo("PL");
        assertThat(res.get(0).getDevicesCount()).isEqualTo(1L);
        assertThat(res.get(0).getBugsCount()).isEqualTo(2L);
    }
}
