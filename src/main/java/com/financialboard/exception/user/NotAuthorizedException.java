package com.financialboard.exception.user;

public class NotAuthorizedException extends RuntimeException {

    public NotAuthorizedException(String s) {
        super(s);
    }
}
