package com.sajee.bookhub.book.repository;

import com.sajee.bookhub.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    boolean existsByIsbn(String isbn);

    List<Book> findAllById(Iterable<Long> ids);
}
