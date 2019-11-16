package com.project.hubert.testersmatcher.services;

import com.project.hubert.testersmatcher.domain.model.DictionaryItem;
import com.project.hubert.testersmatcher.domain.model.entity.Country;
import com.project.hubert.testersmatcher.domain.model.entity.Device;
import com.project.hubert.testersmatcher.domain.repository.CountriesRepository;
import com.project.hubert.testersmatcher.domain.repository.DevicesRepository;
import com.project.hubert.testersmatcher.services.impl.DictionaryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = DictionaryServiceImpl.class)
@ExtendWith(SpringExtension.class)
public class DictionaryServiceTest {
    @MockBean
    private CountriesRepository countriesRepository;

    @MockBean
    private DevicesRepository devicesRepository;

    @Resource
    private DictionaryService dictionaryService;

    @BeforeEach
    public void setup() {
        reset(countriesRepository, devicesRepository);
    }

    @Test
    public void shouldGetCountriesTest() {
        //given
        Country c = new Country();
        c.setCountryCode("PL");
        when(countriesRepository.findAll()).thenReturn(Collections.singletonList(c));

        //when
        List<DictionaryItem> res = dictionaryService.getCountries();

        //then
        assertThat(res).isNotNull();
        assertThat(res.size()).isEqualTo(1);
        assertThat(res.get(0).getCode()).isEqualTo("PL");
        assertThat(res.get(0).getName()).isEqualTo("PL");
    }

    @Test
    public void shouldGetDevicesTest() {
        //given
        Device d = new Device();
        d.setName("iPhone");
        when(devicesRepository.findAll()).thenReturn(Collections.singletonList(d));

        //when
        List<DictionaryItem> res = dictionaryService.getDevices();

        //then
        assertThat(res).isNotNull();
        assertThat(res.size()).isEqualTo(1);
        assertThat(res.get(0).getCode()).isEqualTo("iPhone");
        assertThat(res.get(0).getName()).isEqualTo("iPhone");
    }
}
