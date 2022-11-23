package com.practice.bookstore.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "Book")
public class Book {
    @Id
    private String id;
    @NonNull
    private Long isbn;
    @NonNull
    private String name;
    private String description;
    @NonNull
    private String author;
    @NonNull private BookType type;
    @NonNull
    private Integer price;

    public Integer getPrice() {
        return type.promotion.apply(price);
    }
}
