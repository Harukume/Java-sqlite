package com.pwr.lab03.kayak.exception;

public class DataException extends RuntimeException {
    public DataException(String message) {
        super(message);
    }

    public DataException(String msg, Throwable cause) { super(msg, cause); }
}

