package com.project.hubert.testersmatcher.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TesterSummaryAccumulator {
    private final Long testerId;
    private final String testerFirstName;
    private final String testerLastName;
    private final String country;
    private final Long devicesCount;
    private final Long bugsCount;
}
