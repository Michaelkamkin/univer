package com.example.university;

public class University {
    private String name;
    private String logoFileName;

    public University(String name, String logoFileName) {
        this.name = name;
        this.logoFileName = logoFileName;
    }

    public String getName() {
        return name;
    }

    public String getLogoFileName() {
        return logoFileName;
    }
}
