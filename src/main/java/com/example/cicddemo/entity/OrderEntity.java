package com.example.cicddemo.entity;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "ORDERS")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_seq_gen")
    @SequenceGenerator(name = "orders_seq_gen", sequenceName = "ORDERS_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CUSTOMER_NAME", nullable = false, length = 200)
    private String customerName;

    @Column(name = "ITEM", nullable = false, length = 200)
    private String item;

    @Column(name = "QUANTITY", nullable = false)
    private Integer quantity;

    @Column(name = "STATUS", nullable = false, length = 40)
    private String status;

    @Column(name = "CREATED_AT", nullable = false)
    private OffsetDateTime createdAt;

    public OrderEntity() {}

    public OrderEntity(String customerName, String item, Integer quantity, String status, OffsetDateTime createdAt) {
        this.customerName = customerName;
        this.item = item;
        this.quantity = quantity;
        this.status = status;
        this.createdAt = createdAt;
    }

    // getters/setters

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