package com.example.guloadssms.models;

public class SMSMessages {
    private String id;
    private String sender;
    private String receiver;
    private String message;
    private boolean status;

    public SMSMessages() {
    }

    public SMSMessages(String id, String sender, String receiver, String message, boolean status) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
