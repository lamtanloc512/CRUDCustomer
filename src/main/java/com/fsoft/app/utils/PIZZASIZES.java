package com.fsoft.app.utils;

public enum PIZZASIZES {
    SMALL("small"), MEDIUM("medium"), LARGE("large");

    PIZZASIZES(String size) {
        this.size = size;
    }

    private String size;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
