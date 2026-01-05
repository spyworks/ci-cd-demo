package com.example.cicddemo.api;

import java.time.OffsetDateTime;

public class OrderResponse {
    private Long id;
    private String customerName;
    private String item;
    private Integer quantity;
    private String status;
    private OffsetDateTime createdAt;

    public OrderResponse() {}

    public OrderResponse(Long id, String customerName, String item, Integer quantity, String status, OffsetDateTime createdAt) {
        this.id = id;
        this.customerName = customerName;
        this.item = item;
        this.quantity = quantity;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getItem() { return item; }
    public void setItem(String item) { this.item = item; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
}