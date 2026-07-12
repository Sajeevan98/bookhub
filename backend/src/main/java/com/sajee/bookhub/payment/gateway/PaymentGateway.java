package com.sajee.bookhub.payment.gateway;

import com.sajee.bookhub.payment.dto.response.PaymentInitiateResponse;
import com.sajee.bookhub.payment.entity.Payment;
import com.sajee.bookhub.payment.enums.PaymentGatewayType;

public interface PaymentGateway {

    /**
     * Which gateway does this implementation support?
     */
    PaymentGatewayType getGateway();

    /**
     * Creates the data required by the frontend
     * to redirect the customer to the payment gateway.
     */
    PaymentInitiateResponse initiatePayment(Payment payment);
}
