package io.space.geek.tms.commons.constant.tms;

import io.space.geek.tms.commons.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

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

    public static TestStatus testStatusByValue(int value) {
        return Arrays.stream(TestStatus.values())
            .filter(status -> status.value == value)
            .findFirst()
            .orElseThrow(ResourceNotFoundException.newResourceNotFoundException("TestStatus", "value", value));
    }

}