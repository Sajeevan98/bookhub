package com.sajee.bookhub.payment.controller;

import com.sajee.bookhub.common.ApiEndpoints;
import com.sajee.bookhub.common.ResponseFactory;
import com.sajee.bookhub.common.SuccessResponse;
import com.sajee.bookhub.payment.dto.request.InitiatePaymentRequest;
import com.sajee.bookhub.payment.dto.response.PaymentInitiateResponse;
import com.sajee.bookhub.payment.dto.response.PaymentResponse;
import com.sajee.bookhub.payment.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(ApiEndpoints.PAYMENTS)
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/initiate")
    public ResponseEntity<SuccessResponse<PaymentInitiateResponse>> initiatePayment(
            @Valid @RequestBody InitiatePaymentRequest request) {

        PaymentInitiateResponse response = paymentService.initiatePayment(request);

        return ResponseFactory.created("Payment initiated successfully", response);
    }

    @GetMapping("/{paymentUuid}")
    public ResponseEntity<SuccessResponse<PaymentResponse>> getPayment(@PathVariable UUID paymentUuid) {

        PaymentResponse response = paymentService.getPayment(paymentUuid);

        return ResponseFactory.ok("Payment retrieved successfully", response);
    }

    // this will return all the payments history of an order
    @GetMapping("/order/{orderUuid}")
    public ResponseEntity<SuccessResponse<List<PaymentResponse>>> getPaymentByOrder(@PathVariable UUID orderUuid) {

        List<PaymentResponse> response = paymentService.getPayments(orderUuid);

        return ResponseFactory.ok("Payments retrieved successfully", response);
    }
}
