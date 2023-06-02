package com.example.university;

public class University {
    private String name;
    private int logoResId;

    public University(String name, int logoResId) {
        this.name = name;
        this.logoResId = logoResId;
    }

    public String getName() {
        return name;
    }

    public int getLogoResId() {
        return logoResId;
    }
}
