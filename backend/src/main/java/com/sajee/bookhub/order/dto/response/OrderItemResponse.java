package com.sajee.bookhub.order.dto.response;

import java.math.BigDecimal;

public record OrderItemResponse(

        String bookTitle,

        Integer quantity,

        BigDecimal unitPrice,

        BigDecimal subtotal
) {
}