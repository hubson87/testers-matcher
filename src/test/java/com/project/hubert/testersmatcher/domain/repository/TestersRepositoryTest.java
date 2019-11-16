package com.project.hubert.testersmatcher.domain.repository;

import com.project.hubert.testersmatcher.domain.model.entity.Tester;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TestersRepositoryTest {
    @PersistenceContext
    EntityManager em;

    @Resource
    private TestersRepository testersRepository;

    @BeforeEach
    public void setup() {
        initData();
    }

    @Test
    public void shouldNotFilterByEmptyValuesTest() {
        //when
        List<Tester> testers = testersRepository.getByCountryCodesAndExclude(null, null);

        //then
        assertThat(testers.size()).isEqualTo(4);
    }

    @Test
    public void shouldNotFilterByCountryAndEmptyTestersTest() {
        //when
        List<Tester> testers = testersRepository.getByCountryCodesAndExclude(Arrays.asList("UK", "PL"), null);

        //then
        assertThat(testers.size()).isEqualTo(2);
        assertThat(testers.stream()).anyMatch(t -> t.getTesterId().equals(3L));
        assertThat(testers.stream()).anyMatch(t -> t.getTesterId().equals(4L));
    }

    @Test
    public void shouldNotFilterByEmptyCountryAndExcludedTestersTest() {
        //when
        List<Tester> testers = testersRepository.getByCountryCodesAndExclude(null, Arrays.asList(3L, 4L));

        //then
        assertThat(testers.size()).isEqualTo(2);
        assertThat(testers.stream().anyMatch(t -> t.getTesterId().equals(1L))).isTrue();
        assertThat(testers.stream().anyMatch(t -> t.getTesterId().equals(2L))).isTrue();
    }

    @Test
    public void shouldNotFilterByCountryAndExcludedTestersTest() {
        //when
        List<Tester> testers = testersRepository.getByCountryCodesAndExclude(Arrays.asList("UK", "PL"),  Arrays.asList(1L, 4L));

        //then
        assertThat(testers.size()).isEqualTo(1);
        assertThat(testers.get(0).getTesterId()).isEqualTo(3L);
    }

    private void initData() {
        initCountries();
        initTesters();
    }

    private void initCountries() {
        List<String> names = Arrays.asList("US", "UK", "PL");
        for (int i = 0; i < names.size(); i++) {
            em.createNativeQuery("INSERT INTO country(id, country_code) VALUES (?, ?)")
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
}
