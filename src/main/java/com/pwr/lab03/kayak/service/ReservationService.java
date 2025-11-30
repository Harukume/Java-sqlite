package com.pwr.lab03.kayak.service;

import com.pwr.lab03.kayak.dao.OfferDAO;
import com.pwr.lab03.kayak.dao.ReservationDAO;
import com.pwr.lab03.kayak.exception.DataException;
import com.pwr.lab03.kayak.exception.ValidationException;
import com.pwr.lab03.kayak.model.Offer;
import com.pwr.lab03.kayak.model.Reservation;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ReservationService {

    private final ReservationDAO reservationDAO = new ReservationDAO();
    private final OfferDAO offerDAO = new OfferDAO();
    // --- Tworzenie rezerwacji ---
    public void makeReservation(int clientId, int offerId, int spots) throws DataException, ValidationException {
        if (spots <= 0) {
            throw new ValidationException("Number of spots must be greater than 0");
        }

        try {
            Reservation r = new Reservation();
            r.setClientId(clientId);
            r.setOfferId(offerId);
            r.setNumberOfSeats(spots);
            r.setStatus("created"); // domyślny status
            reservationDAO.insert(r);
        } catch (SQLException e) {
            throw new DataException("Cannot create reservation", e);
        }
    }

    // --- Pobranie wszystkich rezerwacji dla klienta ---
    public List<Reservation> viewReservations(int clientId) throws DataException {
        try {
            return reservationDAO.findByClient(clientId);
        } catch (SQLException e) {
            throw new DataException("Cannot fetch reservations for client " + clientId, e);
        }
    }

    // --- Pobranie wszystkich rezerwacji (do symulacji czasu) ---
    public List<Reservation> getAllReservations() throws DataException {
        try {
            return reservationDAO.findAll();
        } catch (SQLException e) {
            throw new DataException("Cannot fetch all reservations", e);
        }
    }

    // --- Zmiana statusu ---
    public void confirmReservation(int reservationId) throws DataException {
        changeStatus(reservationId, "confirmed");
    }

    public void startReservation(int reservationId) throws DataException {
        changeStatus(reservationId, "in_progress");
    }

    public void completeReservation(int reservationId) throws DataException {
        changeStatus(reservationId, "completed");
    }

    public void cancelReservation(int reservationId) throws DataException {
        changeStatus(reservationId, "cancelled");
    }

    public void changeStatus(int reservationId, String status) throws DataException {
        try {
            reservationDAO.updateStatus(reservationId, status);
        } catch (SQLException e) {
            throw new DataException("Cannot change status of reservation " + reservationId, e);
        }
    }

    // --- Pobranie rezerwacji po ID ---
    public Reservation getReservationById(int reservationId) throws DataException {
        try {
            return reservationDAO.findById(reservationId);
        } catch (SQLException e) {
            throw new DataException("Cannot fetch reservation " + reservationId, e);
        }
    }

}