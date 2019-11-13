package com.project.hubert.testersmatcher.domain.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "device")
public class Device {
    @Id
    @Column(name = "id")
    private Long deviceId;

    @Column(name = "description", nullable = false, unique = true)
    private String name;
}
