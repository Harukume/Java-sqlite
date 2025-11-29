package com.pwr.lab03.kayak.dao;

import com.pwr.lab03.kayak.model.Client;

import java.sql.*;
import java.util.*;


public class ClientDAO extends BaseDAO<Client> {


    @Override
    protected Client map(ResultSet rs) throws SQLException {
        return new Client(rs.getInt("id"), rs.getString("name"));
    }


    public List<Client> findAll() throws SQLException {
        List<Client> list = new ArrayList<>();
        try (Connection c = getConnection(); Statement st = c.createStatement(); ResultSet rs = st.executeQuery("SELECT * FROM Client")) {
            while (rs.next()) list.add(map(rs));
        }
        return list;
    }


    public Client findById(int id) throws SQLException {
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement("SELECT * FROM Client WHERE id=?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? map(rs) : null;
        }
    }

    public void insert(Client cl) throws SQLException {
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement("INSERT INTO Client(name) VALUES(?)")) {
            ps.setString(1, cl.getName());
            ps.executeUpdate();
        }
    }

    public void update(int id, String name) throws SQLException {
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement("UPDATE Client SET name=? WHERE id=?")) {
            ps.setString(1, name);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement("DELETE FROM Client WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}