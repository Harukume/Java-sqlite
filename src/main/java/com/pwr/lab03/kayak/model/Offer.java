package com.pwr.lab03.kayak.model;

import java.sql.Date;
import java.time.LocalDate;

public class Offer {
    private int id;
    private Date date;
    private String location;
    private int maxPlaces;
    private int freePlaces;

    public Offer() {}
    public Offer(int id,Date date, String location, int maxPlaces, int freePlaces) {
        this.id = id;
        this.date = date;
        this.location = location;
        this.maxPlaces = maxPlaces;
        this.freePlaces = freePlaces;
    }

    // getters/setters
    public int getId() { return id; }

    public void setId(int id){ this.id=id;}

    public Date getDate() { return date; }

    public void setDate(Date date) {
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
        this.freePlaces = freePlaces; }

    @Override
    public String toString() {
        return "Offer{id=" + id + ", date=" + date + ", location='" + location +
                "', maxPlaces=" + maxPlaces + ", freePlaces=" + freePlaces + "}";
    }
}
