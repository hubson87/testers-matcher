package com.project.hubert.testersmatcher.mapper;

import com.project.hubert.testersmatcher.domain.model.DictionaryItem;
import com.project.hubert.testersmatcher.domain.model.entity.Country;
import com.project.hubert.testersmatcher.domain.model.entity.Device;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class DictionaryMapperTest {

    @Test
    public void shouldMapEmptyCountryTest() {
        assertThat(DictionaryMapper.fromCountry(null)).isNull();
    }

    @Test
    public void shouldMapCountryTest() {
        //given
        Country c = new Country();
        c.setCountryCode("PL");

        //when
        DictionaryItem mappedCountry = DictionaryMapper.fromCountry(c);

        //then
        assertThat(mappedCountry).isNotNull();
        assertThat(mappedCountry.getCode()).isEqualTo("PL");
        assertThat(mappedCountry.getName()).isEqualTo("PL");
    }

    @Test
    public void shouldMapEmptyDeviceTest() {
        assertThat(DictionaryMapper.fromDevice(null)).isNull();
    }

    @Test
    public void shouldMapDeviceTest() {
        //given
        Device d = new Device();
        d.setName("iPhone");

        //when
        DictionaryItem mappedDevice = DictionaryMapper.fromDevice(d);

        //then
        assertThat(mappedDevice).isNotNull();
        assertThat(mappedDevice.getCode()).isEqualTo("iPhone");
        assertThat(mappedDevice.getName()).isEqualTo("iPhone");
    }
}
