package com.financialboard.exception.category;

public class NotFoundCategoryException extends IllegalArgumentException {
    public NotFoundCategoryException(String s) {
        super(s);
    }
}
