package com.sakancom.source;

public enum Type {
    ADMINISTRATOR(1),
    OWNER(2),
    TENANT(3);

    private final int number;
    Type(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
