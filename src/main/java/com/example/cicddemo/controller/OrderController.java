package com.example.cicddemo.controller;

import com.example.cicddemo.api.CreateOrderRequest;
import com.example.cicddemo.api.OrderResponse;
import com.example.cicddemo.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse create(@Valid @RequestBody CreateOrderRequest req) {
        return orderService.create(req);
    }

    @GetMapping("/{id}")
    public OrderResponse get(@PathVariable("id") long id) {
        return orderService.getById(id);
    }

    @GetMapping
    public List<OrderResponse> list() {
        return orderService.list();
    }
}