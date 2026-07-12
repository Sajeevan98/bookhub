package com.sajee.bookhub.order.service.imp;

import com.sajee.bookhub.book.entity.Book;
import com.sajee.bookhub.book.repository.BookRepository;
import com.sajee.bookhub.exception.ResourceNotFoundException;
import com.sajee.bookhub.exception.business.InsufficientStockException;
import com.sajee.bookhub.order.dto.request.OrderItemRequest;
import com.sajee.bookhub.order.dto.request.OrderRequest;
import com.sajee.bookhub.order.dto.response.OrderResponse;
import com.sajee.bookhub.order.entity.Order;
import com.sajee.bookhub.order.entity.OrderItem;
import com.sajee.bookhub.order.enums.OrderStatus;
import com.sajee.bookhub.order.mapper.OrderMapper;
import com.sajee.bookhub.order.repository.OrderRepository;
import com.sajee.bookhub.order.service.OrderService;
import com.sajee.bookhub.order.util.OrderNumberGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImp implements OrderService {

    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;
    private final OrderMapper orderMapper;
    private final OrderNumberGenerator orderNumberGenerator;

    @Override
    public OrderResponse createOrder(OrderRequest request) {
        Order order = Order.builder()
                .uuid(UUID.randomUUID())
                .orderNumber(orderNumberGenerator.generate())
                .customerName(request.customerName())
                .customerEmail(request.customerEmail())
                .status(OrderStatus.PENDING)
                .totalAmount(BigDecimal.ZERO)
                .build();

        BigDecimal total = BigDecimal.ZERO;

        for (OrderItemRequest dto : request.items()) {

            Book book = bookRepository.findById(dto.bookId())
                    .orElseThrow(() ->
                            ResourceNotFoundException.forEntity("Book", dto.bookId()));

            if (book.getStock() < dto.quantity()) {
                throw new InsufficientStockException(book.getTitle(), dto.quantity(), book.getStock());
            }

            BigDecimal subtotal = book.getPrice()
                    .multiply(BigDecimal.valueOf(dto.quantity()));

            OrderItem item = OrderItem.builder()
                    .book(book)
                    .quantity(dto.quantity())
                    .unitPrice(book.getPrice())
                    .subtotal(subtotal)
                    .build();

            order.addItem(item);

            total = total.add(subtotal);
        }
        order.setTotalAmount(total);

        Order savedOrder = orderRepository.save(order);

        return orderMapper.toResponse(savedOrder);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderResponse getOrder(UUID uuid) {

        Order order = orderRepository.findByUuid(uuid)
                .orElseThrow(() ->
                        ResourceNotFoundException.forEntity("Order", uuid));

        return orderMapper.toResponse(order);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse> getOrders() {

        return orderRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(orderMapper::toResponse)
                .toList();
    }
}
