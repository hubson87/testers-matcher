package com.project.hubert.testersmatcher.initializer.jpa.impl;

import com.project.hubert.testersmatcher.initializer.jpa.DbInitializer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class DbInitializerImpl implements DbInitializer {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void initializeCountries(List<Map<String, String>> countries) {
        countries.forEach(country -> {
            entityManager.createNativeQuery("INSERT INTO country(id, country_code) VALUES (?, ?)")
                    .setParameter(1, country.get("id"))
                    .setParameter(2, country.get("name"))
                    .executeUpdate();
        });
    }

    @Override
    public void initializeDevices(List<Map<String, String>> devices) {
        devices.forEach(device -> {
            entityManager.createNativeQuery("INSERT INTO device(id, description) VALUES (?, ?)")
                    .setParameter(1, device.get("deviceId"))
                    .setParameter(2, device.get("description"))
                    .executeUpdate();
        });
    }

    @Override
    public void initializeTesters(List<Map<String, String>> testers) {
        testers.forEach(tester -> {
            entityManager.createNativeQuery("INSERT INTO tester(id, first_name, last_name, country_id, last_login) VALUES (?, ?, ?, ?, ?)")
                    .setParameter(1, tester.get("testerId"))
                    .setParameter(2, tester.get("firstName"))
                    .setParameter(3, tester.get("lastName"))
                    .setParameter(4, tester.get("country"))
                    .setParameter(5, tester.get("lastLogin"))
                    .executeUpdate();
        });
    }

    @Override
    public void initializeTestersDevices(List<Map<String, String>> testersDevices) {
        testersDevices.forEach(testerDevice -> {
            entityManager.createNativeQuery("INSERT INTO tester_devices(device_id, tester_id) VALUES (?, ?)")
                    .setParameter(1, testerDevice.get("deviceId"))
                    .setParameter(2, testerDevice.get("testerId"))
                    .executeUpdate();
        });
    }

    @Override
    public void initializeBugs(List<Map<String, String>> bugs) {
        bugs.forEach(bug -> {
            entityManager.createNativeQuery("INSERT INTO bug(id, device_id, tester_id) VALUES (?, ?, ?)")
                    .setParameter(1, bug.get("bugId"))
                    .setParameter(2, bug.get("deviceId"))
                    .setParameter(3, bug.get("testerId"))
                    .executeUpdate();
        });
    }
}
