package com.practice.bookstore.service;

public enum Promotion {
    TEN_PERCENT {
        public Integer apply(Integer price) {
            double discountedPrice = price - price*0.1;
            return (int) discountedPrice;
        }
    },
    ZERO_PERCENT {
        public Integer apply(Integer price) {
            return price;
        }
    };
    public abstract Integer apply(Integer price);
}
