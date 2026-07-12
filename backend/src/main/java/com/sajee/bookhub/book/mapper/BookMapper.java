package com.sajee.bookhub.book.mapper;

import com.sajee.bookhub.book.dto.request.BookRequest;
import com.sajee.bookhub.book.dto.response.BookResponse;
import com.sajee.bookhub.book.entity.Book;

public class BookMapper {

    private BookMapper() {

    }

    public static Book toEntity(BookRequest request) {

        return Book.builder()
                .title(request.title())
                .author(request.author())
                .price(request.price())
                .stock(request.stock())
                .description(request.description())
                .build();
    }

    public static void updateEntity(Book book, BookRequest request) {

        book.setTitle(request.title());
        book.setAuthor(request.author());
        book.setPrice(request.price());
        book.setStock(request.stock());
        book.setDescription(request.description());
    }

    public static BookResponse toResponse(Book book) {

        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getPrice(),
                book.getStock(),
                book.getDescription(),
                book.getCreatedAt(),
                book.getUpdatedAt()
        );
    }
}
