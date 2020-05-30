package io.space.geek.tms.commons.constant.tms;

import io.space.geek.tms.commons.exception.ResourceNotFoundException;

import java.util.Arrays;

public enum PriorityTestCase {

    HIGH(1),
    MEDIUM(2),
    LOW(3);

    private int id;

    PriorityTestCase(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static PriorityTestCase priorityById(int id) {
        return Arrays.stream(PriorityTestCase.values()).filter(pr -> pr.id == id).findFirst()
            .orElseThrow(ResourceNotFoundException.newResourceNotFoundException("Priority test story", "id", id));
    }
}
