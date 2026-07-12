package com.sajee.bookhub.payment.webhook.service;

import com.sajee.bookhub.book.service.BookService;
import com.sajee.bookhub.exception.ResourceNotFoundException;
import com.sajee.bookhub.exception.business.PaymentAlreadyPendingException;
import com.sajee.bookhub.order.entity.Order;
import com.sajee.bookhub.order.entity.OrderItem;
import com.sajee.bookhub.order.enums.OrderStatus;
import com.sajee.bookhub.payment.entity.Payment;
import com.sajee.bookhub.payment.enums.PaymentStatus;
import com.sajee.bookhub.payment.gateway.payhere.PayHereStatusCode;
import com.sajee.bookhub.payment.repository.PaymentRepository;
import com.sajee.bookhub.payment.webhook.dto.PayHereWebhookRequest;
import com.sajee.bookhub.payment.webhook.verify.SignatureVerifier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class PayHereWebhookServiceImpl implements PayHereWebhookService {

    private final PaymentRepository paymentRepository;
    private final SignatureVerifier signatureVerifier;
    private final BookService bookService;


    @Override
    public void process(PayHereWebhookRequest request) {

        if (!signatureVerifier.verify(request)) {
            throw new PaymentAlreadyPendingException("Invalid payment signature");
        }

        Payment payment = paymentRepository
                .findByPaymentReference(request.order_id())
                .orElseThrow(() ->
                        ResourceNotFoundException.forEntity(
                                "Payment",
                                request.order_id()));

        // Idempotency
        if (payment.getStatus() == PaymentStatus.COMPLETED) {
            return;
        }

        if (PayHereStatusCode.SUCCESS.equals(request.status_code())) {

            payment.setStatus(PaymentStatus.COMPLETED);
            payment.setGatewayTransactionId(request.payment_id());
            payment.setGatewayStatusMessage(request.status_message());
            payment.setPaidAt(LocalDateTime.now());

            Order order = payment.getOrder();

            order.setStatus(OrderStatus.PAID);

            for (OrderItem item : order.getItems()) {
                bookService.reduceStock(
                        item.getBook().getId(),
                        item.getQuantity());
            }

        } else {

            payment.setStatus(PaymentStatus.FAILED);
        }
    }
}
