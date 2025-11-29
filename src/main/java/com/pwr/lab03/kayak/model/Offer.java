package com.pwr.lab03.kayak.model;

import java.time.LocalDate;

public class Offer {
    private int id;
    private LocalDate date;
    private String location;
    private int maxPlaces;
    private int freePlaces;

    public Offer() {}
    public Offer(LocalDate date, String location, int maxPlaces) {
        this.date = date;
        this.location = location;
        this.maxPlaces = maxPlaces;
        this.freePlaces = maxPlaces;
    }

    // getters/setters
    public int getId() { return id; }

    public LocalDate getDate() { return date; }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getLocation() { return location; }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getMaxPlaces() { return maxPlaces; }

    public void setMaxPlaces(int maxPlaces) {
        this.maxPlaces = maxPlaces;
    }

    public int getFreePlaces() { return freePlaces; }
    public void setFreePlaces(int freePlaces) {
        this.freePlaces = freePlaces;
    }

    @Override
    public String toString() {
        return "Offer{id=" + id + ", date=" + date + ", location='" + location +
                "', maxPlaces=" + maxPlaces + ", freePlaces=" + freePlaces + "}";
    }
}
