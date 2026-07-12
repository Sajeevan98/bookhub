package com.sajee.bookhub.order.dto.response;

import com.sajee.bookhub.order.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record OrderResponse(

        UUID uuid,

        String orderNumber,

        String customerName,

        String customerEmail,

        OrderStatus status,

        BigDecimal totalAmount,

        List<OrderItemResponse> items,

        LocalDateTime createdAt
) {
}