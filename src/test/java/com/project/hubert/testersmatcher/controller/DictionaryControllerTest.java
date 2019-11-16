package com.project.hubert.testersmatcher.controller;

import com.project.hubert.testersmatcher.domain.model.DictionaryItem;
import com.project.hubert.testersmatcher.domain.model.entity.Country;
import com.project.hubert.testersmatcher.services.DictionaryService;
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
import java.util.Collections;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = DictionaryController.class)
public class DictionaryControllerTest {
    private static final String BASE_URL = "/dictionary/all";

    @MockBean
    private DictionaryService dictionaryService;

    @Resource
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        reset(dictionaryService);
    }

    @Test
    public void shouldGetAllDictionariesTest() throws Exception {
        //given
        when(dictionaryService.getCountries()).thenReturn(Collections.singletonList(DictionaryItem.builder().code("UK").name("UK").build()));
        when(dictionaryService.getDevices()).thenReturn(Collections.singletonList(DictionaryItem.builder().code("IPhone").name("IPhone").build()));

        //when
        ResultActions actions = mockMvc.perform(get(BASE_URL)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE));

        //then
        actions.andExpect(status().is(200));
        actions.andExpect(content().json("{\"countries\":[{\"code\":\"UK\",\"name\":\"UK\"}],\"devices\":[{\"code\":\"IPhone\",\"name\":\"IPhone\"}]}"));
    }
}
