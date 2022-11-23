package com.practice.bookstore.domain;

import com.practice.bookstore.model.Book;
import com.practice.bookstore.model.BookType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.practice.bookstore.service.Promotion.TEN_PERCENT;

class CartTest {

    @Test
    public void shouldAddItemToCart() {
        Cart cart = new Cart();
        Book book = Book.builder()
                .id("book-id-1")
                .type(BookType.COMIC)
                .name("book-1")
                .author("author-1")
                .price(100)
                .isbn(111L)
                .build();
        cart.addBook(book);

        Assertions.assertEquals(cart.getBooks().size(), 1);
    }

    @Test
    public void shouldReturnCartTotal() {
        Cart cart = new Cart();
        Book book1 = Book.builder()
                .id("book-id-1")
                .name("book-1")
                .type(BookType.COMIC)
                .author("author-1")
                .price(100)
                .isbn(111L)
                .build();
        Book book2 = Book.builder()
                .id("book-id-2")
                .type(BookType.COMIC)
                .name("book-2")
                .author("author-2")
                .price(200)
                .isbn(2222L)
                .build();
        cart.addBook(book1);
        cart.addBook(book2);

        Assertions.assertEquals(cart.total(Optional.empty()), 300);
    }

    @Test
    public void shouldReturnCartTotalWithPromotion() {
        Cart cart = new Cart();
        Book book1 = Book.builder()
                .id("book-id-1")
                .type(BookType.COMIC)
                .name("book-1")
                .author("author-1")
                .price(100)
                .isbn(111L)
                .build();
        Book book2 = Book.builder()
                .id("book-id-2")
                .type(BookType.COMIC)
                .name("book-2")
                .author("author-2")
                .price(200)
                .isbn(2222L)
                .build();
        cart.addBook(book1);
        cart.addBook(book2);

        Assertions.assertEquals(cart.total(Optional.of(TEN_PERCENT)), 270);
    }
}