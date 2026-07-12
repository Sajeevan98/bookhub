package com.sajee.bookhub.book.dto.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record BookRequest(

        @NotBlank
        @Size(max = 255)
        String title,

        @NotBlank
        @Size(max = 150)
        String author,

//        @NotBlank
//        @Size(max = 20)
//        String isbn,

        @DecimalMin("0.01")
        BigDecimal price,

        @Min(0)
        Integer stock,

        @Size(max = 1000)
        String description
) {
}
