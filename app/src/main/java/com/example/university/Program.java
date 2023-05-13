package com.example.university;

public class Program {
    private int id;
    private String name;
    private int universityId;

    public Program(int id, String name, int universityId) {
        this.id = id;
        this.name = name;
        this.universityId = universityId;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getUniversityId() {
        return this.universityId;
    }
}
