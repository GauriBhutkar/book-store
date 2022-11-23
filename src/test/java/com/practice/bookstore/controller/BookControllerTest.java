package com.practice.bookstore.controller;

import com.practice.bookstore.model.Book;
import com.practice.bookstore.model.BookPriceUpdateRequest;
import com.practice.bookstore.model.BookType;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    public void setup() {
        restTemplate.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());
    }

    @Test
    public void testFetchAllBooks(@Autowired MongoTemplate mongoTemplate) {
        mongoTemplate.dropCollection("Book");
        Book book = Book.builder()
                .id("book-id-1")
                .name("book-1")
                .type(BookType.COMIC)
                .author("author-1")
                .price(100)
                .isbn(111L)
                .build();
        mongoTemplate.insert(book);
        ResponseEntity<String> response = this.restTemplate.getForEntity("/books", String.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody(), "[{\"id\":\"book-id-1\",\"isbn\":111,\"name\":\"book-1\",\"description\":null,\"author\":\"author-1\",\"type\":\"COMIC\",\"price\":100}]");
    }


    @Test
    public void testGetBookById(@Autowired MongoTemplate mongoTemplate) {
        mongoTemplate.dropCollection("Book");
        Book book = Book.builder()
                .id("book-id-2")
                .name("book-1")
                .type(BookType.COMIC)
                .author("author-1")
                .price(100)
                .isbn(111L)
                .build();
        mongoTemplate.insert(book);
        ResponseEntity<String> response = this.restTemplate.getForEntity("/books/book-id-2", String.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody(), "{\"id\":\"book-id-2\",\"isbn\":111,\"name\":\"book-1\",\"description\":null,\"author\":\"author-1\",\"type\":\"COMIC\",\"price\":100}");
    }

    @Test
    public void shouldReturn404IfBookNotFound(@Autowired MongoTemplate mongoTemplate) {
        ResponseEntity<String> response = this.restTemplate.getForEntity("/books/dummy-book", String.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void testInsertBook(@Autowired MongoTemplate mongoTemplate) {
        Book book = Book.builder()
                .id("book-id-3")
                .name("book-1")
                .type(BookType.COMIC)
                .author("author-1")
                .price(100)
                .isbn(111L)
                .build();
        mongoTemplate.insert(book);
        ResponseEntity<String> response = this.restTemplate.postForEntity("/books", book, String.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody(), "{\"id\":\"book-id-3\",\"isbn\":111,\"name\":\"book-1\",\"description\":null,\"author\":\"author-1\",\"type\":\"COMIC\",\"price\":100}");
    }

    @Test
    public void testUpdateBook(@Autowired MongoTemplate mongoTemplate) {
        mongoTemplate.dropCollection("Book");
        Book book = Book.builder()
                .id("book-id-4")
                .name("book-1")
                .type(BookType.COMIC)
                .author("author-1")
                .price(100)
                .isbn(111L)
                .build();
        mongoTemplate.insert(book);
        BookPriceUpdateRequest request = new BookPriceUpdateRequest(200);
        String response = this.restTemplate.patchForObject("/books/bookId", request, String.class);

        Assertions.assertEquals(response, "{\"id\":\"book-id-4\",\"isbn\":111,\"name\":\"book-1\",\"description\":null,\"author\":\"author-1\",\"type\":\"COMIC\",\"price\":200}");
    }

    @Test
    public void testDeleteBook(@Autowired MongoTemplate mongoTemplate) {
        mongoTemplate.dropCollection("Book");
        Book book = Book.builder()
                .id("book-id-1")
                .name("book-1")
                .type(BookType.COMIC)
                .author("author-1")
                .price(100)
                .isbn(111L)
                .build();
        mongoTemplate.insert(book);
        this.restTemplate.delete("/books/book-id-1");

        List<Book> books = mongoTemplate.findAll(Book.class);
        Assertions.assertEquals(books.size(), 0);
    }

}