package com.practice.bookstore.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BookTest {

    @Test
    public void shouldReturnDiscountedPriceForFictionBook() {
        Book book = Book.builder()
                .id("book-id-1")
                .type(BookType.FICTION)
                .name("book-1")
                .author("author-1")
                .price(100)
                .isbn(111L)
                .build();

        Assertions.assertEquals(book.getPrice(), 90);
    }

    @Test
    public void shouldReturnPriceForComicBook() {
        Book book = Book.builder()
                .id("book-id-1")
                .type(BookType.COMIC)
                .name("book-1")
                .author("author-1")
                .price(100)
                .isbn(111L)
                .build();

        Assertions.assertEquals(book.getPrice(), 100);
    }
}