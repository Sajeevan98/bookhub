package com.sajee.bookhub.payment.service.imp;

import com.sajee.bookhub.exception.ResourceNotFoundException;
import com.sajee.bookhub.exception.business.OrderAlreadyPaidException;
import com.sajee.bookhub.exception.business.PaymentAlreadyPendingException;
import com.sajee.bookhub.order.entity.Order;
import com.sajee.bookhub.order.enums.OrderStatus;
import com.sajee.bookhub.order.repository.OrderRepository;
import com.sajee.bookhub.payment.dto.request.InitiatePaymentRequest;
import com.sajee.bookhub.payment.dto.response.PaymentInitiateResponse;
import com.sajee.bookhub.payment.dto.response.PaymentResponse;
import com.sajee.bookhub.payment.entity.Payment;
import com.sajee.bookhub.payment.enums.PaymentStatus;
import com.sajee.bookhub.payment.gateway.PaymentGateway;
import com.sajee.bookhub.payment.gateway.PaymentGatewayFactory;
import com.sajee.bookhub.payment.mapper.PaymentMapper;
import com.sajee.bookhub.payment.repository.PaymentRepository;
import com.sajee.bookhub.payment.service.PaymentService;
import com.sajee.bookhub.payment.util.PaymentReferenceGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImp implements PaymentService {

    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentGatewayFactory gatewayFactory;
    private final PaymentReferenceGenerator paymentReferenceGenerator;
    private final PaymentMapper paymentMapper;

    @Override
    public PaymentInitiateResponse initiatePayment(InitiatePaymentRequest request) {

        Order order = orderRepository.findByUuid(request.orderUuid())
                .orElseThrow(() ->
                        ResourceNotFoundException.forEntity("Order", request.orderUuid()));

        if (order.getStatus() == OrderStatus.PAID) {
            throw new OrderAlreadyPaidException("Order is already paid.") {
            };
        }

        Payment latestPayment = getLatestPayment(order);
        validatePaymentAttempt(latestPayment);

        Payment payment = Payment.builder()
                .uuid(UUID.randomUUID())
                .order(order)
                .gateway(request.gateway())
                .status(PaymentStatus.PENDING)
                .amount(order.getTotalAmount())
                .paymentReference(paymentReferenceGenerator.generate())
                .build();

        paymentRepository.save(payment);

        PaymentGateway gateway = gatewayFactory.getGateway(request.gateway());

        return gateway.initiatePayment(payment);
    }

    @Override
    @Transactional(readOnly = true)
    public PaymentResponse getPayment(UUID paymentUuid) {

        Payment payment = paymentRepository.findByUuid(paymentUuid)
                .orElseThrow(() ->
                        ResourceNotFoundException.forEntity(
                                "Payment",
                                paymentUuid));

        return paymentMapper.toResponse(payment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentResponse> getPayments(UUID orderUuid) {

        List<Payment> payments =
                paymentRepository.findAllByOrder_UuidOrderByCreatedAtDesc(orderUuid);

        if (payments.isEmpty()) {
            throw ResourceNotFoundException.forEntity("Payment", orderUuid);
        }

        return payments.stream()
                .map(paymentMapper::toResponse)
                .toList();
    }

    private Payment getLatestPayment(Order order) {

        List<Payment> payments =
                paymentRepository.findAllByOrder_UuidOrderByCreatedAtDesc(order.getUuid());

        if (payments.isEmpty()) {
            return null;
        }

        return payments.get(0);
    }

    private void validatePaymentAttempt(Payment latestPayment) {

        if (latestPayment == null) {
            return;
        }

        switch (latestPayment.getStatus()) {

            case COMPLETED -> throw new OrderAlreadyPaidException("This order has already been paid");

            case PENDING -> throw new PaymentAlreadyPendingException("A payment is already pending for this order");

            case FAILED, CANCELLED -> {
                // Allow creating a new payment attempt
            }
        }
    }
}
