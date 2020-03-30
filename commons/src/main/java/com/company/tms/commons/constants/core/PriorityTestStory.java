package com.company.tms.commons.constants.core;

public enum PriorityTestStory {

    HIGH(1),
    MEDIUM(2),
    LOW(3);

    private int id;

    PriorityTestStory(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
