package com.sajee.bookhub.order.service;

import com.sajee.bookhub.order.dto.request.OrderRequest;
import com.sajee.bookhub.order.dto.response.OrderResponse;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    OrderResponse createOrder(OrderRequest request);

    OrderResponse getOrder(UUID uuid);

    List<OrderResponse> getOrders();
}
