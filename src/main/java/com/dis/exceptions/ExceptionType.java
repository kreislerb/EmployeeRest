package com.dis.exceptions;

public enum ExceptionType {

    EMPLOYEE_EXCPETION("Employee Error"),
    DIS_EXCEPTION("DIS Aplication Error");

    String type;

    ExceptionType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}