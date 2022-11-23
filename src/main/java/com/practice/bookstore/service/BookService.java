package com.practice.bookstore.service;

import com.practice.bookstore.exception.ResourceNotFoundException;
import com.practice.bookstore.model.Book;
import com.practice.bookstore.model.BookPriceUpdateRequest;
import com.practice.bookstore.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(String id, BookPriceUpdateRequest bookPriceUpdateRequest) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not exist with id: " + id));
        book.setPrice(bookPriceUpdateRequest.getPrice());
        bookRepository.save(book);
        return book;
    }

    public Book getBookBy(String id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not exist with id: " + id));
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public void deleteBook(String id) {
        bookRepository.deleteById(id);
    }
}
