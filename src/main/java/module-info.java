module kayak {
requires java.sql;
    requires java.desktop;

    exports com.pwr.lab03.kayak.model;

exports com.pwr.lab03.kayak.dao;

exports com.pwr.lab03.kayak.service;

exports com.pwr.lab03.kayak.app;

exports com.pwr.lab03.kayak.exception;
}