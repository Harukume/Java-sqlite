package com.pwr.lab03.kayak.dao;
import com.pwr.lab03.kayak.util.DbManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class BaseDAO<T> {
    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:kayak.db");
    }


    protected abstract T map(ResultSet rs) throws SQLException;
}