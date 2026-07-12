package com.sajee.bookhub.book.controller;

import com.sajee.bookhub.common.ApiEndpoints;
import com.sajee.bookhub.common.ResponseFactory;
import com.sajee.bookhub.book.dto.request.BookRequest;
import com.sajee.bookhub.book.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiEndpoints.BOOKS)
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody BookRequest request) {

        return ResponseFactory.created(
                "Book created successfully",
                bookService.create(request));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {

        return ResponseFactory.ok(
                    "Books fetched successfully",
                bookService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {

        return ResponseFactory.ok(
                "Book fetched successfully",
                bookService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody BookRequest request) {

        return ResponseFactory.ok(
                "Book updated successfully",
                bookService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        bookService.delete(id);
        return ResponseFactory.noContent();
    }
}
