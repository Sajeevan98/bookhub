package com.sajee.bookhub.book.service.imp;

import com.sajee.bookhub.book.entity.Book;
import com.sajee.bookhub.book.repository.BookRepository;
import com.sajee.bookhub.book.service.BookDomainService;
import com.sajee.bookhub.exception.ResourceNotFoundException;
import com.sajee.bookhub.exception.business.InsufficientStockException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookDomainServiceImpl implements BookDomainService {

    private final BookRepository bookRepository;

    @Override
    public Book getBook(Long id) {

        return bookRepository.findById(id)
                .orElseThrow(() ->
                        ResourceNotFoundException.forEntity("Book", id));
    }

    @Override
    public void validateStock(Book book, int quantity) {

        if (book.getStock() < quantity) {
            throw new InsufficientStockException(
                    book.getTitle(),
                    quantity,
                    book.getStock()
            );
        }
    }
}
