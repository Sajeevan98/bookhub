package com.sajee.bookhub.payment.entity;

import com.sajee.bookhub.common.BaseEntity;
import com.sajee.bookhub.order.entity.Order;
import com.sajee.bookhub.payment.enums.PaymentGatewayType;
import com.sajee.bookhub.payment.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private UUID uuid;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private PaymentGatewayType gateway;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private PaymentStatus status;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "payment_reference", nullable = false, unique = true)
    private String paymentReference;

    @Column(name = "gateway_transaction_id")
    private String gatewayTransactionId;

    @Column(name = "gateway_status_message")
    private String gatewayStatusMessage;

    @Column(name = "paid_at")
    private LocalDateTime paidAt;

    // Many payments can belong to one order
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}
