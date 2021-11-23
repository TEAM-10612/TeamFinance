package com.financialboard.exception.category;

public class NotParentCategoryException extends IllegalArgumentException {
    public NotParentCategoryException(String s) {
        super(s);
    }
}
