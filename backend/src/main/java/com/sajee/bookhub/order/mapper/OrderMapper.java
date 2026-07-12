package com.sajee.bookhub.order.mapper;

import com.sajee.bookhub.order.dto.response.OrderItemResponse;
import com.sajee.bookhub.order.dto.response.OrderResponse;
import com.sajee.bookhub.order.entity.Order;
import com.sajee.bookhub.order.entity.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderResponse toResponse(Order order) {

        return new OrderResponse(
                order.getUuid(),
                order.getOrderNumber(),
                order.getCustomerName(),
                order.getCustomerEmail(),
                order.getStatus(),
                order.getTotalAmount(),
                order.getItems()
                        .stream()
                        .map(this::toItemResponse)
                        .toList(),
                order.getCreatedAt()
        );
    }

    private OrderItemResponse toItemResponse(OrderItem item) {

        return new OrderItemResponse(
                item.getBook().getTitle(),
                item.getQuantity(),
                item.getUnitPrice(),
                item.getSubtotal()
        );
    }
}
