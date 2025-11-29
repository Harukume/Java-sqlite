package com.pwr.lab03.kayak.dao;


import com.pwr.lab03.kayak.model.Task;
import java.sql.*;
import java.util.*;


public class TaskDAO extends BaseDAO<Task> {


    @Override
    protected Task map(ResultSet rs) throws SQLException {
        return new Task(
                rs.getInt("id"),
                rs.getInt("organizerId"),
                rs.getInt("employeeId"),
                rs.getInt("offerId"),
                rs.getString("status")
        );
    }


    public List<Task> findOpen() throws SQLException {
        List<Task> list = new ArrayList<>();
        try (Connection c = getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM Task WHERE status='open'")) {
            while (rs.next()) list.add(map(rs));
        }
        return list;
    }


    public void insert(Task t) throws SQLException {
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(
                     "INSERT INTO Task(organizerId, employeeId, offerId, status) VALUES(?,?,?,?)")) {
            ps.setInt(1, t.getOrganizerId());
            ps.setInt(2, t.getEmployeeId());
            ps.setInt(3, t.getOfferId());
            ps.setString(4, t.getStatus());
            ps.executeUpdate();
        }
    }


    public void updateStatus(int id, String status) throws SQLException {
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement("UPDATE Task SET status=? WHERE id=?")) {
            ps.setString(1, status);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }


    public void delete(int id) throws SQLException {
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement("DELETE FROM Task WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}