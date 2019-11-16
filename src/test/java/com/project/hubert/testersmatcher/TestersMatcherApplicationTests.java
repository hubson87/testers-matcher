package com.project.hubert.testersmatcher;

import com.project.hubert.testersmatcher.controller.DictionaryController;
import com.project.hubert.testersmatcher.controller.StatisticsController;
import com.project.hubert.testersmatcher.domain.model.DictionaryItem;
import com.project.hubert.testersmatcher.representation.DictionariesRepresentation;
import com.project.hubert.testersmatcher.representation.DictionaryItemRepresentation;
import com.project.hubert.testersmatcher.representation.StatisticsRepresentation;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TestersMatcherApplicationTests {

    @Resource
    public StatisticsController statisticsController;

    @Resource
    public DictionaryController dictionaryController;

    @Test
    void contextLoads() {
    }

    @Test
    void shouldGetUsersForEmptyValuesIntegrationTest() {
        //given
        List<String> countries = new ArrayList<>();
        List<String> devices = new ArrayList<>();

        //when
        List<StatisticsRepresentation> res = statisticsController.getStatistics(countries, devices);

        //then
        assertThat(res).isNotNull();
        assertThat(res.size()).isEqualTo(9);

        assertRepresentation(res.get(0), 125, "Taybin", "Rutkin", "US", 2);
        assertRepresentation(res.get(1), 117, "Lucas", "Lowry", "JP", 5);
        assertRepresentation(res.get(2), 116, "Sean", "Wellington", "JP", 5);
        assertRepresentation(res.get(3), 114, "Miguel", "Bautista", "US", 4);
        assertRepresentation(res.get(4), 110, "Stanley", "Chen", "GB", 1);
        assertRepresentation(res.get(5), 109, "Mingquan", "Zheng", "JP", 5);
        assertRepresentation(res.get(6), 106, "Leonard", "Sutton", "GB", 4);
        assertRepresentation(res.get(7), 104, "Darshini", "Thiagarajan", "GB", 4);
        assertRepresentation(res.get(8), 99, "Michael", "Lubavin", "US", 6);
    }

    @Test
    void shouldGetUsersForSelectedCountriesIntegrationTest() {
        //given
        List<String> countries = Collections.singletonList("JP");
        List<String> devices = new ArrayList<>();

        //when
        List<StatisticsRepresentation> res = statisticsController.getStatistics(countries, devices);

        //then
        assertThat(res).isNotNull();
        assertThat(res.size()).isEqualTo(3);

        assertRepresentation(res.get(0), 117, "Lucas", "Lowry", "JP", 5);
        assertRepresentation(res.get(1), 116, "Sean", "Wellington", "JP", 5);
        assertRepresentation(res.get(2), 109, "Mingquan", "Zheng", "JP", 5);
    }

    @Test
    void shouldGetUsersForSelectedDevicesIntegrationTest() {
        //given
        List<String> countries = new ArrayList<>();
        List<String> devices = Collections.singletonList("Droid DNA");

        //when
        List<StatisticsRepresentation> res = statisticsController.getStatistics(countries, devices);

        //then
        assertThat(res).isNotNull();
        assertThat(res.size()).isEqualTo(9);

        assertRepresentation(res.get(0), 25, "Darshini", "Thiagarajan", "GB", 1);
        assertRepresentation(res.get(1), 21, "Lucas", "Lowry", "JP", 1);
        assertRepresentation(res.get(2), 12, "Michael", "Lubavin", "US", 1);
        assertRepresentation(res.get(3), 0, "Miguel", "Bautista", "US", 0);
        assertRepresentation(res.get(4), 0, "Leonard", "Sutton", "GB", 0);
        assertRepresentation(res.get(5), 0, "Taybin", "Rutkin", "US", 0);
        assertRepresentation(res.get(6), 0, "Mingquan", "Zheng", "JP", 0);
        assertRepresentation(res.get(7), 0, "Stanley", "Chen", "GB", 0);
        assertRepresentation(res.get(8), 0, "Sean", "Wellington", "JP", 0);
    }

    @Test
    void shouldGetUsersForSelectedCountriesAndDevicesIntegrationTest() {
        //given
        List<String> countries = Collections.singletonList("GB");
        List<String> devices = Collections.singletonList("iPhone 5");

        //when
        List<StatisticsRepresentation> res = statisticsController.getStatistics(countries, devices);

        //then
        assertThat(res).isNotNull();
        assertThat(res.size()).isEqualTo(3);

        assertRepresentation(res.get(0), 110, "Stanley", "Chen", "GB", 1);
        assertRepresentation(res.get(1), 32, "Leonard", "Sutton", "GB", 1);
        assertRepresentation(res.get(2), 0, "Darshini", "Thiagarajan", "GB", 0);
    }

    @Test
    void shouldGetDictionariesTest() {
        List<String> expectedCountries = Arrays.asList("GB", "US", "JP");
        List<String> expectedDevicesCountries = Arrays.asList("iPhone 4", "iPhone 4S", "iPhone 5", "Galaxy S3", "Galaxy S4", "Nexus 4",
                "Droid Razor", "Droid DNA", "HTC One", "iPhone 3");
        //when
        DictionariesRepresentation res = dictionaryController.getAllDictionaries();

        //then
        assertThat(res).isNotNull();
        assertThat(res.getCountries()).isNotEmpty();
        assertThat(res.getCountries().size()).isEqualTo(expectedCountries.size());
        expectedCountries.forEach(c -> assertThat(res.getCountries()).contains(DictionaryItemRepresentation.builder().code(c).name(c).build()));
        assertThat(res.getDevices().size()).isEqualTo(expectedDevicesCountries.size());
        expectedDevicesCountries.forEach(d -> assertThat(res.getDevices()).contains(DictionaryItemRepresentation.builder().code(d).name(d).build()));

    }

    private void assertRepresentation(StatisticsRepresentation res, long bugs, String firstName, String lastName, String country, long devices) {
        assertThat(res.getBugsCount()).isEqualTo(bugs);
        assertThat(res.getTesterFirstName()).isEqualTo(firstName);
        assertThat(res.getTesterLastName()).isEqualTo(lastName);
        assertThat(res.getCountry()).isEqualTo(country);
        assertThat(res.getDevicesCount()).isEqualTo(devices);
    }

}
