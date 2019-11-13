package com.project.hubert.testersmatcher.domain.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TesterSummaryAccumulator {
    private String testerFirstName;
    private String testerLastName;
    private String country;
    private Long devicesCount;
    private Long bugsCount;

    public String getOutput() {
        return String.format("%s %s (Country: %s) (Devices %d) => %d", testerFirstName, testerLastName, country, devicesCount, bugsCount);
    }
}
