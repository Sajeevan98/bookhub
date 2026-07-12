package com.sajee.bookhub.payment.mapper;

import com.sajee.bookhub.payment.dto.response.PaymentResponse;
import com.sajee.bookhub.payment.entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public PaymentResponse toResponse(Payment payment) {

        return new PaymentResponse(

                payment.getUuid(),

                payment.getPaymentReference(),

                payment.getOrder().getOrderNumber(),

                payment.getGateway(),

                payment.getStatus(),

                payment.getAmount(),

                payment.getGatewayTransactionId(),

                payment.getGatewayStatusMessage(),

                payment.getPaidAt(),

                payment.getCreatedAt()
        );
    }
}
