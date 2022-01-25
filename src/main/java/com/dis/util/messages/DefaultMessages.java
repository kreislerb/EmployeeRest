package com.dis.util.messages;

public enum DefaultMessages {

    SUCESS_REQUEST("Success");

    String message;

    DefaultMessages(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
