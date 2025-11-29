package com.pwr.lab03.kayak.model;

public class Reservation {
    private int id;
    private int clientId;
    private int offerId;
    private int numberOfSeats;
    private String status; // założona, potwierdzona, realizowana, zrealizowana, odwołana
    private String createdAt;
    public Reservation() {}

    public Reservation(int clientId, int offerId, int numberOfSeats, String status) {
        this.clientId = clientId;
        this.offerId = offerId;
        this.numberOfSeats = numberOfSeats;
        this.status = status;
    }

    // getters/setters
    public int getId() { return id; }

    public int getClientId() { return clientId; }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getOfferId() { return offerId; }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public int getNumberOfSeats() { return numberOfSeats; }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return "Reservation{id=" + id + ", clientId=" + clientId + ", offerId=" + offerId +
                ", numberOfSeats=" + numberOfSeats + ", status='" + status + "'}";
    }
}
