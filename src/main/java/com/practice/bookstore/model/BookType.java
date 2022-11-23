package com.practice.bookstore.model;

import com.practice.bookstore.service.Promotion;

public enum BookType {
    FICTION(Promotion.TEN_PERCENT),
    COMIC(Promotion.ZERO_PERCENT);
    public final Promotion promotion;

    private BookType(Promotion promotion) {
        this.promotion = promotion;
    }
}
