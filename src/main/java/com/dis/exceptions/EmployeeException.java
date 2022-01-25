package com.dis.exceptions;

public class EmployeeException extends DisRuntimeException{

    private String description;

    @Override
    public String getType() {
        return ExceptionType.EMPLOYEE_EXCPETION.getType();
    }

    @Override
    public String getDescription() {
        return description;
    }

    public EmployeeException(String description) {
        this.description = description;
    }

    public EmployeeException(String description, Throwable e) {
        super(e);
        this.description = description;
    }
}
