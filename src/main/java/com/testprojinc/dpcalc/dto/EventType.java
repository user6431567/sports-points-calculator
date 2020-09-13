package com.testprojinc.dpcalc.dto;

import java.util.stream.Stream;

public enum EventType {
    TRACK("T"), FIELD("F");

    private final String type;

    EventType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static EventType fromString(String type) {
        if (null == type) {
            return null;
        }

        return Stream.of(values())
                .filter(o -> o.getType().equalsIgnoreCase(type))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
