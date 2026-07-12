package com.sajee.bookhub.book.service;

import com.sajee.bookhub.book.entity.Book;

public interface BookDomainService {

    Book getBook(Long id);

    void validateStock(Book book, int quantity);
}
