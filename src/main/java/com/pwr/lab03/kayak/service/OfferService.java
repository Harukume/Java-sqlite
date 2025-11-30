package com.pwr.lab03.kayak.service;

import com.pwr.lab03.kayak.dao.OfferDAO;
import com.pwr.lab03.kayak.exception.DataException;
import com.pwr.lab03.kayak.exception.ValidationException;
import com.pwr.lab03.kayak.model.Offer;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class OfferService {

    private final OfferDAO offerDAO = new OfferDAO();

    public List<Offer> getAllOffers() throws SQLException, DataException {
        try {
            return offerDAO.findAll();
        } catch (SQLException e) {
            throw new DataException("Error fetching offers: " + e.getMessage());
        }
    }

    public Offer getOfferById(int id) throws SQLException, DataException {
        try {
            Offer offer = offerDAO.findById(id);
            if (offer == null)
                throw new ValidationException("Offer with id " + id + " not found");
            return offer;
        } catch (SQLException e) {
            throw new DataException("Database error: " + e.getMessage());
        }
    }

    public void addOffer(String date, String location, int maxPlaces) throws SQLException, DataException {
        if (date == null)
            throw new ValidationException("Date required");

        if (location == null || location.isBlank())
            throw new ValidationException("Location required");

        if (maxPlaces <= 0)
            throw new ValidationException("Max places must be > 0");

        Offer offer = new Offer(0, date, location, maxPlaces, maxPlaces);

        try {
            offerDAO.insert(offer);
        } catch (SQLException e) {
            throw new DataException("Error inserting offer: " + e.getMessage());
        }
    }

    public void updateOffer(Offer offer) throws SQLException, DataException {
        if (offer == null)
            throw new ValidationException("Offer is null");

        if (offer.getMaxPlaces() < offer.getFreePlaces())
            throw new ValidationException("Max places cannot be less than free places");

        try {
            offerDAO.updateFreeSpots(offer.getId(), offer.getFreePlaces());
        } catch (SQLException e) {
            throw new DataException("Error updating offer: " + e.getMessage());
        }
    }

    public void deleteOffer(int id) throws SQLException, DataException {
        try {
            offerDAO.delete(id);
        } catch (SQLException e) {
            throw new DataException("Error deleting offer: " + e.getMessage());
        }
    }

}
