package com.sajee.bookhub.book.service.imp;

import com.sajee.bookhub.book.dto.request.BookRequest;
import com.sajee.bookhub.book.dto.response.BookResponse;
import com.sajee.bookhub.book.entity.Book;
import com.sajee.bookhub.book.util.IsbnGenerator;
import com.sajee.bookhub.exception.BusinessException;
import com.sajee.bookhub.exception.DuplicateResourceException;
import com.sajee.bookhub.exception.ResourceNotFoundException;
import com.sajee.bookhub.book.mapper.BookMapper;
import com.sajee.bookhub.book.repository.BookRepository;
import com.sajee.bookhub.book.service.BookService;
import com.sajee.bookhub.exception.business.InsufficientStockException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class BookServiceImp implements BookService {

    private final BookRepository bookRepository;
    private final IsbnGenerator isbnGenerator;
    private final BookDomainServiceImpl bookDomainService;

    @Override
    public BookResponse create(BookRequest request) {

        String isbn = isbnGenerator.generate();
        if (bookRepository.existsByIsbn(isbn)) {
            throw new DuplicateResourceException(
                    "Book with ISBN - " + isbn + " already exists");
        }

        Book book = BookMapper.toEntity(request);
        book.setIsbn(isbn);
        return BookMapper.toResponse(bookRepository.save(book));
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookResponse> findAll() {

        return bookRepository.findAll()
                .stream()
                .map(BookMapper::toResponse) // map(data->BookMapper.toResponse(data))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public BookResponse findById(Long id) {

        return BookMapper.toResponse(findBook(id));
    }

    @Override
    public BookResponse update(Long id, BookRequest request) {

        Book book = findBook(id);
        BookMapper.updateEntity(book, request);
        return BookMapper.toResponse(bookRepository.save(book));
    }

    @Override
    public void delete(Long id) {
        bookRepository.delete(findBook(id));
    }

    @Override
    public void reduceStock(Long bookId, Integer quantity) {

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> ResourceNotFoundException.forEntity("Book", bookId));

        bookDomainService.validateStock(book, quantity);

        book.setStock(book.getStock() - quantity);
    }

    private Book findBook(Long id) {

        return bookRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Book not found with id: " + id));
    }
}
