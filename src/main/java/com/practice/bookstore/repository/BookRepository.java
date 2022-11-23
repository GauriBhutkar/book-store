package com.practice.bookstore.repository;

import com.practice.bookstore.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface BookRepository extends MongoRepository<Book, String> {}
