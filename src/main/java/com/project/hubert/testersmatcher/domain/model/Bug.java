package com.project.hubert.testersmatcher.domain.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "bug")
public class Bug {
    @Id
    @Column(name = "id")
    private Long bugId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "device_id")
    private Device device;
}
