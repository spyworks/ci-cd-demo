package com.example.cicddemo.service;

import com.example.cicddemo.api.CreateOrderRequest;
import com.example.cicddemo.api.OrderResponse;
import com.example.cicddemo.entity.OrderEntity;
import com.example.cicddemo.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository repo;

    public OrderService(OrderRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public OrderResponse create(CreateOrderRequest req) {
        OrderEntity saved = repo.save(new OrderEntity(
                req.getCustomerName(),
                req.getItem(),
                req.getQuantity(),
                "LODGED",
                OffsetDateTime.now()
        ));
        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    public OrderResponse getById(long id) {
        OrderEntity e = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + id));
        return toResponse(e);
    }

    @Transactional(readOnly = true)
    public List<OrderResponse> list() {
        return repo.findAll().stream().map(this::toResponse).toList();
    }

    private OrderResponse toResponse(OrderEntity e) {
        return new OrderResponse(
                e.getId(),
                e.getCustomerName(),
                e.getItem(),
                e.getQuantity(),
                e.getStatus(),
                e.getCreatedAt()
        );
    }
}