package com.pwr.lab03.kayak.service;


import com.pwr.lab03.kayak.dao.ClientDAO;
import com.pwr.lab03.kayak.dao.OfferDAO;
import com.pwr.lab03.kayak.dao.ReservationDAO;
import com.pwr.lab03.kayak.model.Client;
import com.pwr.lab03.kayak.model.Offer;
import com.pwr.lab03.kayak.model.Reservation;
import com.pwr.lab03.kayak.exception.DataException;
import com.pwr.lab03.kayak.exception.ValidationException;


import java.sql.SQLException;
import java.util.List;


public class ClientService {
    private final ClientDAO clientDAO = new ClientDAO();
    private final OfferDAO offerDAO = new OfferDAO();
    private final ReservationDAO reservationDAO = new ReservationDAO();


    public List<Offer> viewOffers() throws DataException {
        try {
            return offerDAO.findAll();
        } catch (SQLException e) {
            throw new DataException("Error fetching offers", e);
        }
    }


    public void makeReservation(int clientId, int offerId, int spots) throws DataException, ValidationException {
        try {
            Offer offer = offerDAO.findById(offerId);
            if (offer == null) throw new ValidationException("Offer not found");
            if (spots > offer.getFreePlaces()) throw new ValidationException("Not enough places available");


            Reservation r = new Reservation(0, clientId, offer.getId(), offerId, spots, "created");
            reservationDAO.insert(r);


            offerDAO.updateFreeSpots(offerId, offer.getFreePlaces() - spots);
        } catch (SQLException e) {
            throw new DataException("Error making reservation", e);
        }
    }

    public List<Reservation> viewReservations(int clientId) throws DataException {
        try {
            return reservationDAO.findByClient(clientId);
        } catch (SQLException e) {
            throw new DataException("Error fetching reservations", e);
        }
    }
}