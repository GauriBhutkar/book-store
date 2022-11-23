package com.practice.bookstore.service;

import com.practice.bookstore.exception.ResourceNotFoundException;
import com.practice.bookstore.model.Book;
import com.practice.bookstore.model.BookPriceUpdateRequest;
import com.practice.bookstore.model.BookType;
import com.practice.bookstore.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;

class BookServiceTest {
    BookRepository bookRepository = Mockito.mock(BookRepository.class);
    BookService bookService = new BookService(bookRepository);

    @Test
    public void shouldSaveBookInBookRepository() {
        Book book = Book.builder()
                .id("book-id-1")
                .type(BookType.COMIC)
                .name("book-1")
                .author("author-1")
                .price(100)
                .isbn(111L)
                .build();
        doReturn(book).when(bookRepository).save(book);
        Book result = bookService.addBook(book);

        Assertions.assertEquals(book, result);
    }

    @Test
    public void shouldUpdateBookInBookRepository() {
        Book book = Book.builder()
                .id("book-id-1")
                .type(BookType.COMIC)
                .name("book-1")
                .author("author-1")
                .price(100)
                .isbn(111L)
                .build();
        Book updatedBook = Book.builder()
                .id("book-id-1")
                .type(BookType.COMIC)
                .name("book-1")
                .author("author-1")
                .price(200)
                .isbn(111L)
                .build();
        doReturn(Optional.of(book)).when(bookRepository).findById(book.getId());
        doReturn(updatedBook).when(bookRepository).save(updatedBook);
        Book result = bookService.updateBook("book-id-1", new BookPriceUpdateRequest(200));

        Assertions.assertEquals(updatedBook, result);
    }

    @Test
    public void shouldGetBookById() {
        Book book = Book.builder()
                .id("book-id-1")
                .type(BookType.COMIC)
                .name("book-1")
                .author("author-1")
                .price(100)
                .isbn(111L)
                .build();

        doReturn(Optional.of(book)).when(bookRepository).findById(book.getId());
        Book result = bookService.getBookBy("book-id-1");

        Assertions.assertEquals(book, result);
    }

    @Test
    public void shouldThrowExceptionIfBookNotFoundForGivenId() {
        Book book = Book.builder()
                .id("book-id-1")
                .type(BookType.COMIC)
                .name("book-1")
                .author("author-1")
                .price(100)
                .isbn(111L)
                .build();

        doReturn(Optional.empty()).when(bookRepository).findById(book.getId());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> bookService.getBookBy("book-id-1"));
    }

    @Test
    public void shouldGetAllBooks() {
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
                .price(100)
                .isbn(111L)
                .build();

        doReturn(Arrays.asList(book1, book2)).when(bookRepository).findAll();
        List<Book> result = bookService.getBooks();

        Assertions.assertEquals(2, result.size());
    }

}