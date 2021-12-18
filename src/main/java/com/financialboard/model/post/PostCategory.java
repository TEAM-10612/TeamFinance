package com.financialboard.model.post;

public enum PostCategory {
    STOCK("주식"), REAL_ESTATE("부동산"),COIN("코인"),LOAN("대출"),
    FREE_BOARD("자유게시판");

    private String name;

    PostCategory(String name) {
        this.name = name;
    }
    public String getName(){
        return name;
    }

}
