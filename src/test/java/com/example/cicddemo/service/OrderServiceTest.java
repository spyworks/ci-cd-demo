package com.example.cicddemo.service;

import com.example.cicddemo.api.CreateOrderRequest;
import com.example.cicddemo.entity.OrderEntity;
import com.example.cicddemo.repository.OrderRepository;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    @Test
    void create_shouldSaveAndReturnResponse() {
        OrderRepository repo = mock(OrderRepository.class);

        OrderEntity saved = new OrderEntity("Alice", "Book", 2, "LODGED", OffsetDateTime.now());
        saved.setId(123L);

        when(repo.save(any(OrderEntity.class))).thenReturn(saved);

        OrderService svc = new OrderService(repo);

        CreateOrderRequest req = new CreateOrderRequest();
        req.setCustomerName("Alice");
        req.setItem("Book");
        req.setQuantity(2);

        var res = svc.create(req);

        assertThat(res.getId()).isEqualTo(123L);
        assertThat(res.getCustomerName()).isEqualTo("Alice");
        assertThat(res.getItem()).isEqualTo("Book");
        assertThat(res.getQuantity()).isEqualTo(2);
        assertThat(res.getStatus()).isEqualTo("LODGED");

        verify(repo, times(1)).save(any(OrderEntity.class));
    }
}