package com.project.hubert.testersmatcher.domain.repository;

import com.project.hubert.testersmatcher.domain.model.TesterSummaryAccumulator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BugsRepositoryTest {
    @PersistenceContext
    EntityManager em;

    @Resource
    private BugsRepository bugsRepository;

    @BeforeEach
    public void setup() {
        initData();
    }

    @Test
    public void shouldNotFilterOnEmptyValuesTest() {
        //when
        List<TesterSummaryAccumulator> res = bugsRepository.findBugsByTesterCountryIdsAndDeviceIds(null, null);

        //then
        assertThat(res).isNotNull();
        assertThat(res.size()).isEqualTo(4);
        assertThat(res.get(0).getTesterFirstName()).isEqualTo("User4");
        assertThat(res.get(0).getBugsCount()).isEqualTo(7L);
        assertThat(res.get(1).getTesterFirstName()).isEqualTo("User1");
        assertThat(res.get(1).getBugsCount()).isEqualTo(6L);
        assertThat(res.get(2).getTesterFirstName()).isEqualTo("User2");
        assertThat(res.get(2).getBugsCount()).isEqualTo(5L);
        assertThat(res.get(3).getTesterFirstName()).isEqualTo("User3");
        assertThat(res.get(3).getBugsCount()).isEqualTo(4L);
    }

    @Test
    public void shouldFilterByCountriesTest() {
        //when
        List<TesterSummaryAccumulator> res = bugsRepository.findBugsByTesterCountryIdsAndDeviceIds(Arrays.asList("US", "PL"), null);

        //then
        assertThat(res).isNotNull();
        assertThat(res.size()).isEqualTo(3);
        assertThat(res.get(0).getTesterFirstName()).isEqualTo("User4");
        assertThat(res.get(0).getBugsCount()).isEqualTo(7L);
        assertThat(res.get(1).getTesterFirstName()).isEqualTo("User1");
        assertThat(res.get(1).getBugsCount()).isEqualTo(6L);
        assertThat(res.get(2).getTesterFirstName()).isEqualTo("User2");
        assertThat(res.get(2).getBugsCount()).isEqualTo(5L);
    }

    @Test
    public void shouldFilterByDevicesTest() {
        //when
        List<TesterSummaryAccumulator> res = bugsRepository.findBugsByTesterCountryIdsAndDeviceIds(null, Arrays.asList("iPhone4", "iPhone6"));

        //then
        assertThat(res).isNotNull();
        assertThat(res.size()).isEqualTo(3);
        assertThat(res.get(0).getTesterFirstName()).isEqualTo("User4");
        assertThat(res.get(0).getBugsCount()).isEqualTo(7L);
        assertThat(res.get(1).getTesterFirstName()).isEqualTo("User3");
        assertThat(res.get(1).getBugsCount()).isEqualTo(4L);
        assertThat(res.get(2).getTesterFirstName()).isEqualTo("User1");
        assertThat(res.get(2).getBugsCount()).isEqualTo(2L);
    }

    @Test
    public void shouldFilterByCountriesAndDevicesTest() {
        //when
        List<TesterSummaryAccumulator> res = bugsRepository.findBugsByTesterCountryIdsAndDeviceIds(Collections.singletonList("US"), Arrays.asList("iPhone4", "iPhone6"));

        //then
        assertThat(res).isNotNull();
        assertThat(res.size()).isEqualTo(1);
        assertThat(res.get(0).getTesterFirstName()).isEqualTo("User1");
        assertThat(res.get(0).getBugsCount()).isEqualTo(2L);
    }

    private void initData() {
        initCountries();
        initDevices();
        initTesters();
        initTesterDevices();
        initBugs();
    }

    private void initCountries() {
        List<String> names = Arrays.asList("US", "UK", "PL");
        for (int i = 0; i < names.size(); i++) {
            em.createNativeQuery("INSERT INTO country(id, country_code) VALUES (?, ?)")
                    .setParameter(1, i + 1).setParameter(2, names.get(i)).executeUpdate();
        }
    }

    private void initDevices() {
        List<String> names = Arrays.asList("iPhone4", "iPhone5", "iPhone6");
        for (int i = 0; i < names.size(); i++) {
            em.createNativeQuery("INSERT INTO device(id, description) VALUES (?, ?)")
                    .setParameter(1, i + 1).setParameter(2, names.get(i)).executeUpdate();
        }
    }

    private void initTesters() {
        initializeTester(1L, "User1", "Name1", 1L);
        initializeTester(2L, "User2", "Name2", 1L);
        initializeTester(3L, "User3", "Name3", 2L);
        initializeTester(4L, "User4", "Name4", 3L);
    }

    private void initializeTester(Long testerId, String firstName, String lastName, Long country) {
        em.createNativeQuery("INSERT INTO tester(id, first_name, last_name, country_id, last_login) VALUES (?, ?, ?, ?, ?)")
                .setParameter(1, testerId)
                .setParameter(2, firstName)
                .setParameter(3, lastName)
                .setParameter(4, country)
                .setParameter(5, LocalDateTime.now())
                .executeUpdate();
    }

    private void initTesterDevices() {
        Map<Long, List<Long>> testerDevices = new HashMap<>();
        testerDevices.put(1L, Arrays.asList(1L, 2L));
        testerDevices.put(2L, Collections.singletonList(2L));
        testerDevices.put(3L, Collections.singletonList(3L));
        testerDevices.put(4L, Collections.singletonList(3L));
        testerDevices.forEach((key, value) -> value.forEach(
                e -> em.createNativeQuery("INSERT INTO tester_devices(device_id, tester_id) VALUES (?, ?)")
                        .setParameter(1, e)
                        .setParameter(2, key)
                        .executeUpdate()));

    }

    private void initBugs() {
        int i = 1;
        //user 1 from US has 2 bugs for iphone4 and 4 bugs for iphone5
        insertIntoBugs(i++, 1, 1);
        insertIntoBugs(i++, 1, 1);
        insertIntoBugs(i++, 2, 1);
        insertIntoBugs(i++, 2, 1);
        insertIntoBugs(i++, 2, 1);
        insertIntoBugs(i++, 2, 1);
        //user 2 from US has 5 bugs for iphone5
        insertIntoBugs(i++, 2, 2);
        insertIntoBugs(i++, 2, 2);
        insertIntoBugs(i++, 2, 2);
        insertIntoBugs(i++, 2, 2);
        insertIntoBugs(i++, 2, 2);
        //user 3 from UK has 4 bugs for iphone6
        insertIntoBugs(i++, 3, 3);
        insertIntoBugs(i++, 3, 3);
        insertIntoBugs(i++, 3, 3);
        insertIntoBugs(i++, 3, 3);
        //user 4 from PL has 7 bugs for iphone6
        insertIntoBugs(i++, 3, 4);
        insertIntoBugs(i++, 3, 4);
        insertIntoBugs(i++, 3, 4);
        insertIntoBugs(i++, 3, 4);
        insertIntoBugs(i++, 3, 4);
        insertIntoBugs(i++, 3, 4);
        insertIntoBugs(i, 3, 4);
    }

    private void insertIntoBugs(long id, long device, long tester) {
        em.createNativeQuery("INSERT INTO bug(id, device_id, tester_id) VALUES (?, ?, ?)")
                .setParameter(1, id).setParameter(2, device).setParameter(3, tester).executeUpdate();
    }
}
