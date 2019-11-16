package com.project.hubert.testersmatcher.services;

import com.project.hubert.testersmatcher.domain.model.TesterSummaryAccumulator;
import com.project.hubert.testersmatcher.domain.model.entity.Country;
import com.project.hubert.testersmatcher.domain.model.entity.Tester;
import com.project.hubert.testersmatcher.domain.repository.BugsRepository;
import com.project.hubert.testersmatcher.domain.repository.TestersRepository;
import com.project.hubert.testersmatcher.services.impl.StatisticServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = StatisticServiceImpl.class)
@ExtendWith(SpringExtension.class)
public class StatisticsServiceTest {

    @MockBean
    private BugsRepository bugsRepository;

    @MockBean
    private TestersRepository testersRepository;

    @Resource
    private StatisticService statisticService;

    @BeforeEach
    public void setup() {
        reset(bugsRepository, testersRepository);
    }

    @Test
    public void shouldFindNoBugsNoTestersTest() {
        //when
        List<TesterSummaryAccumulator> res = statisticService.getStatistics(null, null);

        //then
        assertThat(res).isNotNull();
        assertThat(res).isEmpty();
    }

    @Test
    public void shouldFindEmptyTestersOnlyTest() {
        //given
        Tester t = new Tester();
        t.setTesterId(1L);
        when(testersRepository.getByCountryCodesAndExclude(anyList(), isNull()))
                .thenReturn(Collections.singletonList(t));

        //when
        List<TesterSummaryAccumulator> res = statisticService.getStatistics(Arrays.asList("UK", "PL"), Arrays.asList("iPhone", "Samsung"));

        //then
        assertThat(res).isNotNull();
        assertThat(res.size()).isEqualTo(1L);
        assertThat(res.get(0).getTesterId()).isEqualTo(1L);
        assertThat(res.get(0).getBugsCount()).isEqualTo(0);
    }

    @Test
    public void shouldFindByBugsOnlyTest() {
        //given
        TesterSummaryAccumulator t = new TesterSummaryAccumulator(1L, "u1", "n1", "PL", 1L, 3L);
        when(bugsRepository.findBugsByTesterCountryIdsAndDeviceIds(anyList(), anyList()))
                .thenReturn(Collections.singletonList(t));

        //when
        List<TesterSummaryAccumulator> res = statisticService.getStatistics(Arrays.asList("UK", "PL"), Arrays.asList("iPhone", "Samsung"));

        //then
        assertThat(res).isNotNull();
        assertThat(res.size()).isEqualTo(1L);
        assertThat(res.get(0).getTesterId()).isEqualTo(1L);
        assertThat(res.get(0).getTesterFirstName()).isEqualTo("u1");
        assertThat(res.get(0).getTesterLastName()).isEqualTo("n1");
        assertThat(res.get(0).getCountry()).isEqualTo("PL");
        assertThat(res.get(0).getBugsCount()).isEqualTo(3L);
    }

    @Test
    public void shouldFindByBugsAndCountryTest() {
        //given
        TesterSummaryAccumulator testAcc = new TesterSummaryAccumulator(1L, "u1", "n1", "PL", 1L, 3L);
        Tester t = new Tester();
        t.setTesterId(2L);
        t.setCountry(new Country());
        when(bugsRepository.findBugsByTesterCountryIdsAndDeviceIds(anyList(), anyList())).thenReturn(new ArrayList<>(Collections.singletonList(testAcc)));
        when(testersRepository.getByCountryCodesAndExclude(anyList(), anyList())).thenReturn(Collections.singletonList(t));

        //when
        List<TesterSummaryAccumulator> res = statisticService.getStatistics(Arrays.asList("UK", "PL"), Arrays.asList("iPhone", "Samsung"));

        //then
        assertThat(res).isNotNull();
        assertThat(res.size()).isEqualTo(2);
        assertThat(res.stream()).anyMatch(tst -> tst.getTesterId().equals(1L) && tst.getBugsCount().equals(3L));
        assertThat(res.stream()).anyMatch(tst -> tst.getTesterId().equals(2L) && tst.getBugsCount().equals(0L));
    }
}
