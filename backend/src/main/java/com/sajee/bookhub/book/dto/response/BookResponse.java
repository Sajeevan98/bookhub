package com.sajee.bookhub.book.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record BookResponse(
        Long id,
        String title,
        String author,
        String isbn,
        BigDecimal price,
        Integer stock,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
