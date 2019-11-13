package com.project.hubert.testersmatcher.domain.repository;

import com.project.hubert.testersmatcher.domain.model.TesterSummaryAccumulator;
import com.project.hubert.testersmatcher.domain.model.entity.Bug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BugsRepository extends JpaRepository<Bug, Long> {
    @Query("select " +
            "new com.project.hubert.testersmatcher.domain.model.TesterSummaryAccumulator(" +
            "       b.tester.firstName, " +
            "       b.tester.lastName," +
            "       b.tester.country.countryCode," +
            "       count(distinct b.device)," +
            "       count(b)) " +
            "from Bug b " +
            "where ((:countries) is null or b.tester.country.countryCode in :countries) " +
            "and ((:devices) is null or b.device.name in :devices) " +
            "group by b.tester " +
            "order by count(b) desc")
    List<TesterSummaryAccumulator> findBugsByTesterCountryIdsAndDeviceIds(@Param("countries") List<String> countries, @Param("devices") List<String> devices);
}
