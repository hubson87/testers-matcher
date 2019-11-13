package com.project.hubert.testersmatcher.domain.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"lastLogin", "bugs", "devices"})
@ToString(exclude = "bugs")
@Entity
@Table(name = "tester")
public class Tester {
    @Id
    @Column(name = "id")
    private Long testerId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    private Country country;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "tester_devices",
        joinColumns = @JoinColumn(name = "tester_id"),
        inverseJoinColumns = @JoinColumn(name = "device_id"))
    private Set<Device> devices = new HashSet<>();

    @OneToMany(orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "tester")
    private Set<Bug> bugs = new HashSet<>();
}
