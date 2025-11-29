package com.pwr.lab03.kayak.model;

public class Employee {
    private int id;
    private String name;

    public Employee() {}
    public Employee(String name) { this.name = name; }

    // getters/setters
    public int getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "'}";
    }
}
