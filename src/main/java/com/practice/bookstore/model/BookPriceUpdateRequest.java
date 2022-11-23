package com.practice.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class BookPriceUpdateRequest {
    private final Integer price;
}
