package com.project.hubert.testersmatcher.domain.repository;

import com.project.hubert.testersmatcher.domain.model.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountriesRepository extends JpaRepository<Country, Long> {

}
