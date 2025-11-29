package com.pwr.lab03.kayak.model;

public class Task {
    private int id;
    private int organizerId;
    private int employeeId;
    private int offerId;
    private String status; // otwarte, realizowane, zamknięte

    public Task() {}
    public Task(int organizerId, int employeeId, int offerId, String status) {
        this.organizerId = organizerId;
        this.employeeId = employeeId;
        this.offerId = offerId;
        this.status = status;
    }

    // getters/setters
    public int getId() { return id; }

    public int getOrganizerId() { return organizerId; }

    public void setOrganizerId(int organizerId) {
        this.organizerId = organizerId;
    }

    public int getEmployeeId() { return employeeId; }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getOfferId() { return offerId; }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public String getStatus() { return status; }
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{id=" + id + ", organizerId=" + organizerId + ", employeeId=" + employeeId +
                ", offerId=" + offerId + ", status='" + status + "'}";
    }
}