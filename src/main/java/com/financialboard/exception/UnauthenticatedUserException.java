package com.financialboard.exception;

public class UnauthenticatedUserException extends IllegalArgumentException {
    public UnauthenticatedUserException(String s) {
        super(s);
    }
}
