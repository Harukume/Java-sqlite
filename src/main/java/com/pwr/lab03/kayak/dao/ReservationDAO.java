package com.pwr.lab03.kayak.dao;

import com.pwr.lab03.kayak.model.Reservation;
import java.sql.*;
import java.util.*;


public class ReservationDAO extends BaseDAO<Reservation> {

    @Override
    protected Reservation map(ResultSet rs) throws SQLException {
        return new Reservation(
                rs.getInt("id"),
                rs.getInt("clientId"),
                rs.getInt("organizerId"),
                rs.getInt("offerId"),
                rs.getInt("numberOfSeats"),
                rs.getString("status")
        );
    }

    public List<Reservation> findAll() throws SQLException {
        List<Reservation> list = new ArrayList<>();
        try(Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement("SELECT * FROM Reservation")) {
                ResultSet rs = ps.executeQuery();
                while(rs.next()) list.add(map(rs));

        }
        return list;
    }
    public Reservation findById(int id) throws SQLException {
        try(Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement("SELECT * FROM Reservation where id=?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? map(rs) : null;
        }
    }
    public List<Reservation> findByOffer(int offerId) throws SQLException {
        List<Reservation> list = new ArrayList<>();
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT * FROM Reservation WHERE offerId=?")) {
            ps.setInt(1, offerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(map(rs));
        }
        return list;
    }

    public List<Reservation> findByClient(int clientId) throws SQLException {
        List<Reservation> list = new ArrayList<>();
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT * FROM Reservation WHERE clientId=?")) {
            ps.setInt(1, clientId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(map(rs));
        }
        return list;
    }



    public void insert(Reservation r) throws SQLException {
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(
                     "INSERT INTO Reservation(clientId, organizerId, offerId, numberOfSeats, status) VALUES(?,?,?,?,?)")) {
            ps.setInt(1, r.getClientId());
            ps.setInt(2, r.getOrganizerId());
            ps.setInt(3, r.getOfferId());
            ps.setInt(4, r.getNumberOfSeats());
            ps.setString(5, r.getStatus());
            ps.executeUpdate();
        }
    }

public void updateStatus(int id, String status) throws SQLException {
    try (Connection c = getConnection();
         PreparedStatement ps = c.prepareStatement("UPDATE Reservation SET status=? WHERE id=?")) {
        ps.setString(1, status);
        ps.setInt(2, id);
        ps.executeUpdate();
    }
}


public void delete(int id) throws SQLException {
    try (Connection c = getConnection();
         PreparedStatement ps = c.prepareStatement("DELETE FROM Reservation WHERE id=?")) {
        ps.setInt(1, id);
        ps.executeUpdate();
    }
}

}