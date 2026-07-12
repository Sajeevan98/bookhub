package com.sajee.bookhub.order.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record OrderRequest(

        @NotBlank(message = "Customer name is required")
        String customerName,

        @NotBlank(message = "Customer email is required")
        @Email(message = "Invalid email format")
        String customerEmail,

        @Valid
        @NotEmpty(message = "Order must contain at least one item")
        List<OrderItemRequest> items
) {
}
