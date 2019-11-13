package com.project.hubert.testersmatcher.representation;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class StatisticsRepresentation {
    private final String testerFirstName;
    private final String testerLastName;
    private final String country;
    private final Long devicesCount;
    private final Long bugsCount;
}
