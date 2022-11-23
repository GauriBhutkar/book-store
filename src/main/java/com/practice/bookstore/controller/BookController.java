package com.practice.bookstore.controller;

import com.practice.bookstore.model.Book;
import com.practice.bookstore.model.BookPriceUpdateRequest;
import com.practice.bookstore.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Book addEmployee(@RequestBody Book bookRequest) {
        return bookService.addBook(bookRequest);
    }

    @GetMapping(path= "/{id}", produces = "application/json")
    public Book getBook(@PathVariable String id) {
        return bookService.getBookBy(id);
    }

    @GetMapping(produces = "application/json")
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @PatchMapping(path= "/{id}", consumes = "application/json", produces = "application/json")
    public Book updateBook(@PathVariable String id,@RequestBody BookPriceUpdateRequest bookPriceUpdateRequest) {
        return bookService.updateBook(id, bookPriceUpdateRequest);
    }

    @DeleteMapping(path= "/{id}", produces = "application/json")
    public void deleteBook(@PathVariable String id) {
        bookService.deleteBook(id);
    }
}
