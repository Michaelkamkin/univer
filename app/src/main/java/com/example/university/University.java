package com.example.university;

import java.io.Serializable;

public class University implements Serializable {
    private int id;
    private String name;
    private int cityId;

    public University(int id, String name, int cityId) {
        this.id = id;
        this.name = name;
        this.cityId = cityId;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getCityId() {
        return this.cityId;
    }
}
