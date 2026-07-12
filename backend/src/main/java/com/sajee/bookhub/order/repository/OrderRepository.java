package com.sajee.bookhub.order.repository;

import com.sajee.bookhub.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByUuid(UUID uuid);

    List<Order> findAllByOrderByCreatedAtDesc();

    Optional<Order> findByOrderNumber(String orderNumber);

    boolean existsByOrderNumber(String orderNumber);;
}

// findByUuid() --> frontend requests
// findByOrderNumber() --> customer support / invoices
// existsByOrderNumber() --> future sequential generator