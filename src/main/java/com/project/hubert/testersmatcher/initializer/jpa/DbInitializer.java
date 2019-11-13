package com.project.hubert.testersmatcher.initializer.jpa;

import java.util.List;
import java.util.Map;

public interface DbInitializer {
    void initializeCountries(List<Map<String, String>> countries);

    void initializeDevices(List<Map<String, String>> devices);

    void initializeTesters(List<Map<String, String>> testers);

    void initializeTestersDevices(List<Map<String, String>> testersDevices);

    void initializeBugs(List<Map<String, String>> bugs);
}
