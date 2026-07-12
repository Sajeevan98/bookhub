package com.sajee.bookhub.payment.repository;

import com.sajee.bookhub.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByUuid(UUID uuid);

    Optional<Payment> findByPaymentReference(String paymentReference);

    /**
     * Payment entity doesn't contain: UUID orderUuid. But It contains: Order order;
     * Spring Data JPA understands nested property traversal: findByOrder_Uuid(...)
     * which translates to SQL similar to:
     * SELECT *
     * FROM payments p
     * JOIN orders o ON p.order_id = o.id
     * WHERE o.uuid = ?
     */
    List<Payment> findAllByOrder_UuidOrderByCreatedAtDesc(UUID orderUuid);
}
