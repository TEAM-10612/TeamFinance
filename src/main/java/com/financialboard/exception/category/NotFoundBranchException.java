package com.financialboard.exception.category;

public class NotFoundBranchException extends IllegalArgumentException {
    public NotFoundBranchException(String s) {
        super(s);
    }
}
