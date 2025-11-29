package com.pwr.lab03.kayak.dao;

import com.pwr.lab03.kayak.model.Organizer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrganizerDAO extends BaseDAO<Organizer> {


    @Override
    protected Organizer map(ResultSet rs) throws SQLException {
        return new Organizer(rs.getInt("id"), rs.getString("name"));
    }


    public List<Organizer> findAll() throws SQLException {
        List<Organizer> list = new ArrayList<>();
        try (Connection c = getConnection(); Statement st = c.createStatement(); ResultSet rs = st.executeQuery("SELECT * FROM Organizer")) {
            while (rs.next()) list.add(map(rs));
        }
        return list;
    }


    public Organizer findById(int id) throws SQLException {
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement("SELECT * FROM Organizer WHERE id=?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? map(rs) : null;
        }
    }

    public void insert(Organizer cl) throws SQLException {
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement("INSERT INTO Organizer(name) VALUES(?)")) {
            ps.setString(1, cl.getName());
            ps.executeUpdate();
        }
    }

    public void update(int id, String name) throws SQLException {
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement("UPDATE Organizer SET name=? WHERE id=?")) {
            ps.setString(1, name);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement("DELETE FROM Organizer WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}