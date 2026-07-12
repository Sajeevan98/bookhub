package com.sajee.bookhub.order.controller;

import com.sajee.bookhub.common.ApiEndpoints;
import com.sajee.bookhub.common.ResponseFactory;
import com.sajee.bookhub.common.SuccessResponse;
import com.sajee.bookhub.order.dto.request.OrderRequest;
import com.sajee.bookhub.order.dto.response.OrderResponse;
import com.sajee.bookhub.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(ApiEndpoints.ORDERS)
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<SuccessResponse<OrderResponse>> createOrder(
            @Valid @RequestBody OrderRequest request) {

        return ResponseFactory.created(
                "Order created successfully",
                orderService.createOrder(request));

    }

    @GetMapping("/{uuid}")
    public ResponseEntity<SuccessResponse<OrderResponse>> getOrder(
            @PathVariable UUID uuid) {

        return ResponseFactory.ok(
                "Order fetched successfully",
                orderService.getOrder(uuid));
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<List<OrderResponse>>> getOrders() {

        List<OrderResponse> response = orderService.getOrders();

        return ResponseFactory.ok(
                "Orders retrieved successfully",
                response
        );
    }
}