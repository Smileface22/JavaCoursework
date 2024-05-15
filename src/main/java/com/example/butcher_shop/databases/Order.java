package com.example.butcher_shop.databases;

import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    private String orderDate;
    private String status;
    private int totalCost;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;

    public Long getId() {
        return id;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public User getUser() {
        return user;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public String getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }
    public Order(){}
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
    }

    public Order(String orderDate, String status, int totalCost, User user, List<OrderItem> orderItems) {
        this.orderDate = orderDate;
        this.status = status;
        this.totalCost = totalCost;
        this.user = user;
        this.orderItems = orderItems;
    }

    public Order(Long id, String orderDate, String status, int totalCost, User user, List<OrderItem> orderItems) {
        this.id = id;
        this.orderDate = orderDate;
        this.status = status;
        this.totalCost = totalCost;
        this.user = user;
        this.orderItems = orderItems;
    }
}
