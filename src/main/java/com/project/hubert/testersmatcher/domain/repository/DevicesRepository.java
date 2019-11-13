package com.project.hubert.testersmatcher.domain.repository;

import com.project.hubert.testersmatcher.domain.model.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DevicesRepository extends JpaRepository<Device, Long> {

}
