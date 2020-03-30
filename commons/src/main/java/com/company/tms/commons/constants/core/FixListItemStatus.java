package com.company.tms.commons.constants.core;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.EnumSet;
import java.util.Optional;

@AllArgsConstructor
public enum FixListItemStatus {

    PENDING(0),
    IN_PROGRESS(1),
    DONE(2),
    CLOSED(3);

    @Getter(onMethod = @__(@JsonValue))
    private Integer status;

    public static Optional<FixListItemStatus> fromStatus(Integer status) {
        return EnumSet.allOf(FixListItemStatus.class).stream()
                .filter(t -> t.getStatus().equals(status))
                .findFirst();
    }

    public static Optional<FixListItemStatus> fromStatus(String status) {
        return fromStatus(Integer.valueOf(status));
    }

    @Override
    public String toString() {
        return status.toString();
    }

}