package com.example.demo.enums;

public enum Status {
    INACTIVE((byte) 0),
    ACTIVE((byte) 1);

    private final byte value;

    Status(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }
}
