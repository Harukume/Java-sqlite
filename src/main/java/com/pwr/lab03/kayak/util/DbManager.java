package com.pwr.lab03.kayak.util;


import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.Scanner;


public class DbManager {
    private final String url;


    public DbManager(String dbFilePath) {
        this.url = "jdbc:sqlite:" + dbFilePath;
    }


    public Connection getConnection() throws SQLException {
        Connection c = DriverManager.getConnection(url);
        try (Statement s = c.createStatement()) {
            s.execute("PRAGMA foreign_keys = ON");
        }
        return c;
    }


    public void initIfNeeded() {
        try (Connection c = getConnection()) {
// execute init.sql from resources
            InputStream in = getClass().getClassLoader().getResourceAsStream("init.sql");
            if (in == null) return;
            String sql = new Scanner(in, StandardCharsets.UTF_8).useDelimiter("\\A").next();
            try (Statement s = c.createStatement()) {
                s.executeUpdate(sql);
            }
        } catch (Exception e) {
            throw new RuntimeException("Nie można zainicjować DB: " + e.getMessage(), e);
        }
    }
}