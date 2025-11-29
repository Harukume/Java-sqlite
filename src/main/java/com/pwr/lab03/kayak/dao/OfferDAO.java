package com.pwr.lab03.kayak.dao;


import com.pwr.lab03.kayak.model.Offer;
import java.sql.*;
import java.util.*;


public class OfferDAO extends BaseDAO<Offer> {

    @Override
    protected Offer map(ResultSet rs) throws SQLException {
        return new Offer(
                rs.getInt("id"),
                rs.getDate("date"),
                rs.getString("location"),
                rs.getInt("maxPlaces"),
                rs.getInt("freePlaces")
        );
    }


    public List<Offer> findAll() throws SQLException {
        List<Offer> list = new ArrayList<>();
        try (Connection c = getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM Offer")) {
            while (rs.next()) list.add(map(rs));
        }
        return list;
    }


    public Offer findById(int id) throws SQLException {
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT * FROM Offer WHERE id=?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? map(rs) : null;
        }
    }


    public void insert(Offer o) throws SQLException {
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(
                     "INSERT INTO Offer(date, location, maxPlaces, freePlaces) VALUES(?,?,?,?)")) {
            ps.setDate(1, o.getDate());
            ps.setString(2, o.getLocation());
            ps.setInt(3, o.getMaxPlaces());
            ps.setInt(4, o.getFreePlaces());
            ps.executeUpdate();
        }
    }


    public void updateFreeSpots(int id, int freePlaces) throws SQLException {
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement("UPDATE Offer SET freePlaces=? WHERE id=?")) {
            ps.setInt(1, freePlaces);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }


    public void delete(int id) throws SQLException {
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement("DELETE FROM Offer WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}