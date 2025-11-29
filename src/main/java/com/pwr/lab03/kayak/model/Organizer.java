package com.pwr.lab03.kayak.model;

public class Organizer {
    private int id;
    private String name;

    public Organizer() {}
    public Organizer(String name) {
        this.name = name;
    }

    // getters/setters
    public int getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Organizer{id=" + id + ", name='" + name + "'}";
    }
}