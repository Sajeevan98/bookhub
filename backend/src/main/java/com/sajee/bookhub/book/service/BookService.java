package com.sajee.bookhub.book.service;

import com.sajee.bookhub.book.dto.request.BookRequest;
import com.sajee.bookhub.book.dto.response.BookResponse;

import java.util.List;

public interface BookService {

    BookResponse create(BookRequest request);

    List<BookResponse> findAll();

    BookResponse findById(Long id);

    BookResponse update(Long id, BookRequest request);

    void delete(Long id);

    void reduceStock(Long bookId, Integer quantity);
}
