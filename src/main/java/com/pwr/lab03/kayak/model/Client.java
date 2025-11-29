package com.pwr.lab03.kayak.model;

public class Client {
    private int id;
    private String name;

    public Client() {}
    public Client(String name) { this.name = name; }

    // getters/setters
    public int getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return "Client{id=" + id + ", name='" + name + "'}";
    }
}
