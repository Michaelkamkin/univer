package com.example.university;

import java.io.Serializable;

public class Program implements Serializable {
    private final int id;
    private final String name;

    public Program(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

}
