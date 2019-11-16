package com.project.hubert.testersmatcher.representation.mapper;

import com.project.hubert.testersmatcher.domain.model.DictionaryItem;
import com.project.hubert.testersmatcher.representation.DictionariesRepresentation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class DictionaryRepresentationMapperTest {

    @Test
    public void shouldMapEmptyDictionariesTest() {
        //when
        DictionariesRepresentation res = DictionaryRepresentationMapper.fromDictionaries(null, null);

        //then
        assertThat(res).isNotNull();
        assertThat(res.getCountries()).isNotNull();
        assertThat(res.getCountries()).isEmpty();
        assertThat(res.getDevices()).isNotNull();
        assertThat(res.getDevices()).isEmpty();
    }

    @Test
    public void shouldMapDictionariesTest() {
        //given
        DictionaryItem country = DictionaryItem.builder().code("PL").name("POLAND").build();
        DictionaryItem device = DictionaryItem.builder().code("iph").name("iPhone").build();

        //when
        DictionariesRepresentation res = DictionaryRepresentationMapper.fromDictionaries(Collections.singletonList(country), Collections.singletonList(device));

        //then
        assertThat(res).isNotNull();
        assertThat(res.getCountries()).isNotNull();
        assertThat(res.getCountries().size()).isEqualTo(1);
        assertThat(res.getCountries().get(0).getCode()).isEqualTo("PL");
        assertThat(res.getCountries().get(0).getName()).isEqualTo("POLAND");
        assertThat(res.getDevices()).isNotNull();
        assertThat(res.getDevices().size()).isEqualTo(1);
        assertThat(res.getDevices().get(0).getCode()).isEqualTo("iph");
        assertThat(res.getDevices().get(0).getName()).isEqualTo("iPhone");
    }
}
