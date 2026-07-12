package com.sajee.bookhub.payment.dto.response;

import com.sajee.bookhub.payment.enums.PaymentGatewayType;
import com.sajee.bookhub.payment.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PaymentResponse(

        UUID paymentUuid,

        String paymentReference,

        String orderNumber,

        PaymentGatewayType gateway,

        PaymentStatus status,

        BigDecimal amount,

        String gatewayTransactionId,

        String gatewayStatusMessage,

        LocalDateTime paidAt,

        LocalDateTime createdAt

) {
}