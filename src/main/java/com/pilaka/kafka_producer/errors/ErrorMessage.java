package com.pilaka.kafka_producer.errors;

import java.sql.Timestamp;

public class ErrorMessage extends Throwable {
    private Timestamp timestamp;
    private String message;
    private String details;

    public ErrorMessage(Timestamp timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public ErrorMessage() {

    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
