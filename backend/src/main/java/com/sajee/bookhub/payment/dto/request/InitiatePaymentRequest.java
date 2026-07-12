package com.sajee.bookhub.payment.dto.request;

import com.sajee.bookhub.payment.enums.PaymentGatewayType;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record InitiatePaymentRequest(

        @NotNull(message = "Order UUID is required")
        UUID orderUuid,

        @NotNull(message = "Payment gateway is required")
        PaymentGatewayType gateway

) {
}
