package com.pwr.lab03.kayak.service;

import com.pwr.lab03.kayak.dao.OfferDAO;
import com.pwr.lab03.kayak.dao.ReservationDAO;
import com.pwr.lab03.kayak.dao.TaskDAO;
import com.pwr.lab03.kayak.model.Offer;
import com.pwr.lab03.kayak.model.Reservation;
import com.pwr.lab03.kayak.model.Task;
import com.pwr.lab03.kayak.exception.DataException;
import com.pwr.lab03.kayak.exception.ValidationException;


import java.sql.SQLException;
import java.util.List;


public class OrganizerService {
    private final OfferDAO offerDAO = new OfferDAO();
    private final ReservationDAO reservationDAO = new ReservationDAO();
    private final TaskDAO taskDAO = new TaskDAO();


    public void createOffer(Offer offer) throws DataException {
        try {
            offerDAO.insert(offer);
        } catch (SQLException e) {
            throw new DataException("Error creating offer", e);
        }
    }


    public void confirmReservation(int reservationId) throws DataException, ValidationException {
        try {
            Reservation r = reservationDAO.findById(reservationId);
            if (r == null) throw new ValidationException("Reservation not found");
            r.setStatus("confirmed");
            reservationDAO.updateStatus(reservationId, "confirmed");
        } catch (SQLException e) {
            throw new DataException("Error confirming reservation", e);
        }
    }

    public void cancelReservation(int reservationId) throws DataException {
        try {
            Reservation r = reservationDAO.findById(reservationId);

            if (!r.getStatus().equals("created") && !r.getStatus().equals("confirmed"))
                throw new ValidationException("Cannot cancel now");

            r.setStatus("cancelled");
            reservationDAO.updateStatus( r.getId(), r.getStatus());

        } catch (SQLException e) {
            throw new DataException("Error cancelling reservation: " + e.getMessage());
        }
    }


    public void generateTask(int organizerId, int employeeId, int offerId) throws DataException {
        try {
            Task t = new Task(0, organizerId, employeeId, offerId, "open");
            taskDAO.insert(t);
        } catch (SQLException e) {
            throw new DataException("Error generating task", e);
        }
    }


    public List<Offer> viewOffers() throws DataException {
        try {
            return offerDAO.findAll();
        } catch (SQLException e) {
            throw new DataException("Error fetching offers", e);
        }
    }
}