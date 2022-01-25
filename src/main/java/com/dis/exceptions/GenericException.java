package com.dis.exceptions;

public class GenericException extends DisRuntimeException {

    private static String DEFAULT_MESSAGE = "DIS Aplication has encountered an Exception on the system. The following error(s) have been identified: { %s}";

    private String description;

    @Override
    public String getType() {
        return ExceptionType.DIS_EXCEPTION.getType();
    }

    @Override
    public String getDescription() {

        if (description == null || description.trim().isEmpty())
            return "Internal Server Error";
        else
            return String.format(DEFAULT_MESSAGE, description);
    }

    public GenericException(Exception e) {
        super(e);
    }

    public GenericException(String description, Throwable e) {
        super(e);
        this.description = description;
    }

    public GenericException() {
    }
}