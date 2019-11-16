package com.project.hubert.testersmatcher.controller;

import com.project.hubert.testersmatcher.domain.model.TesterSummaryAccumulator;
import com.project.hubert.testersmatcher.services.StatisticService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.annotation.Resource;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = StatisticsController.class)
public class StatisticsControllerTest {
    private static final String BASE_URL = "/statistics";

    @MockBean
    private StatisticService statisticService;

    @Resource
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        reset(statisticService);
    }

    @Test
    public void shouldGetStatisticsTest() throws Exception {
        //given
        when(statisticService.getStatistics(anyList(), anyList())).thenReturn(Arrays.asList(
                new TesterSummaryAccumulator(1L, "n1", "s1", "c1", 1L, 21L),
                new TesterSummaryAccumulator(2L, "n2", "s2", "c2", 2L, 12L)));

        //when
        ResultActions actions = mockMvc.perform(get(BASE_URL)
                .param("countryCodes", "[]")
                .param("deviceNames", "[]")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE));

        //then
        actions.andExpect(status().is(200));
        actions.andExpect(content().json("[" +
                "{\"testerFirstName\":\"n1\",\"testerLastName\":\"s1\",\"country\":\"c1\",\"devicesCount\":1,\"bugsCount\":21}," +
                "{\"testerFirstName\":\"n2\",\"testerLastName\":\"s2\",\"country\":\"c2\",\"devicesCount\":2,\"bugsCount\":12}" +
                "]"));
    }

    @Test
    public void shouldGetNoStatisticsTest() throws Exception {
        //when
        ResultActions actions = mockMvc.perform(get(BASE_URL)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE));

        //then
        actions.andExpect(status().is(404));
    }
}
