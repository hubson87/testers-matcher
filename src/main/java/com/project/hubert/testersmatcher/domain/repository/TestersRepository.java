package com.project.hubert.testersmatcher.domain.repository;

import com.project.hubert.testersmatcher.domain.model.entity.Tester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TestersRepository extends JpaRepository<Tester, Long> {

    @Query("select t from Tester t where ((:countryCodes) is null or t.country.countryCode in :countryCodes) " +
            "and ((:testerIds) is null or t.testerId not in :testerIds)")
    List<Tester> getByCountryCodesAndExclude(@Param("countryCodes") List<String> countryCodes, @Param("testerIds") List<Long> testerIds);
}
