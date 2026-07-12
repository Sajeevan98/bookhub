package com.sajee.bookhub.payment.service;

import com.sajee.bookhub.payment.dto.request.InitiatePaymentRequest;
import com.sajee.bookhub.payment.dto.response.PaymentInitiateResponse;
import com.sajee.bookhub.payment.dto.response.PaymentResponse;

import java.util.List;
import java.util.UUID;

public interface PaymentService {

    PaymentInitiateResponse initiatePayment(InitiatePaymentRequest request);

    PaymentResponse getPayment(UUID paymentUuid);

    // retrieve history of payments(all payments attempts)
    List<PaymentResponse> getPayments(UUID orderUuid);
}
