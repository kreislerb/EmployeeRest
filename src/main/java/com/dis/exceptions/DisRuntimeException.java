package com.dis.exceptions;

public abstract class DisRuntimeException extends RuntimeException {

    public DisRuntimeException() {
    }

    public DisRuntimeException(Throwable cause) {
        super(cause);
    }

    public abstract String getType();

    public abstract String getDescription();

}