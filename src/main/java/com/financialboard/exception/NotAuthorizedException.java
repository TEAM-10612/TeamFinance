package com.financialboard.exception;

public class NotAuthorizedException extends RuntimeException {

    public NotAuthorizedException(String s) {
        super(s);
    }
}
