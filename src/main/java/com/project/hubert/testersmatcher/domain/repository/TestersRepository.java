package com.project.hubert.testersmatcher.domain.repository;

import com.project.hubert.testersmatcher.domain.model.Tester;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestersRepository extends JpaRepository<Tester, Long> {

}
