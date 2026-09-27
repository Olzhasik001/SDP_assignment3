package com.medical.monitoring.exception;
public class SensorReadException extends RuntimeException {
    public SensorReadException(String message, Throwable cause) {
        super(message, cause);
    }
}