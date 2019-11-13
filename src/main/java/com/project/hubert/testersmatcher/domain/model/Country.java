package com.project.hubert.testersmatcher.domain.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "country")
public class Country {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "country_code", nullable = false)
    private String countryCode;
}
