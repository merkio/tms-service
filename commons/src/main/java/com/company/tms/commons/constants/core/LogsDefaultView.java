package com.company.tms.commons.constants.core;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum LogsDefaultView {

    STEPS(0),
    LOGS(1);

    @Getter(onMethod = @__(@JsonValue))
    private final Integer view;

}