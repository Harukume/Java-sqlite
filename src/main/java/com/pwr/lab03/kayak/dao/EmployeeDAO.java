package com.pwr.lab03.kayak.dao;

import com.pwr.lab03.kayak.model.Employee;
import com.pwr.lab03.kayak.model.Organizer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO extends BaseDAO<Employee> {


    @Override
    protected Employee map(ResultSet rs) throws SQLException {
        return new Employee(rs.getInt("id"), rs.getString("name"));
    }


    public List<Employee> findAll() throws SQLException {
        List<Employee> list = new ArrayList<>();
        try (Connection c = getConnection(); Statement st = c.createStatement(); ResultSet rs = st.executeQuery("SELECT * FROM Employee")) {
            while (rs.next()) list.add(map(rs));
        }
        return list;
    }


    public Employee findById(int id) throws SQLException {
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement("SELECT * FROM Employee WHERE id=?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? map(rs) : null;
        }
    }

    public void insert(Employee cl) throws SQLException {
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement("INSERT INTO Employee(name) VALUES(?)")) {
            ps.setString(1, cl.getName());
            ps.executeUpdate();
        }
    }

    public void update(int id, String name) throws SQLException {
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement("UPDATE Employee SET name=? WHERE id=?")) {
            ps.setString(1, name);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement("DELETE FROM Employee WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}