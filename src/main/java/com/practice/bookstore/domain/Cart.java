package com.practice.bookstore.domain;

import com.practice.bookstore.model.Book;
import com.practice.bookstore.service.Promotion;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cart {

    private List<Book> books = new ArrayList();

    public void addBook(Book b) {
        books.add(b);
    }

    public Integer total(Optional<Promotion> promotion ) {
        Integer total = this.books.stream().mapToInt(Book::getPrice).sum();
        return promotion.isPresent() ? promotion.get().apply(total) : total;
    }

    public List<Book> getBooks() {
        return books;
    }
}
