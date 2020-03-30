package com.company.tms.commons.constants.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TestStatus {

    CREATED(-1),
    SUCCESS(1),
    FAILURE(2),
    SKIP(3),
    SUCCESS_PERCENTAGE_FAILURE(4),
    KNOWN_ISSUE(5),
    STOPPED(10),
    STARTED(16);

    @Getter
    private final int value;

}