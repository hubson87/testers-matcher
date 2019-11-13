package com.project.hubert.testersmatcher.domain.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "bug")
public class Bug {
    @Id
    @Column(name = "id")
    private Long bugId;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;

    @ManyToOne
    @JoinColumn(name = "tester_id")
    private Tester tester;
}
