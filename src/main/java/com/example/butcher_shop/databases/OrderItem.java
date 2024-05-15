package com.example.butcher_shop.databases;


import jakarta.persistence.*;


@Entity
@Table(name = "orderItems")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "meat_id")
    private Meat meat;
    private int quantity;
    public Long getId() {
        return id;
    }
    public Order getOrder() {
        return order;
    }
    public int getQuantity() {
        return quantity;
    }
    public Meat getMeat() {
        return meat;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setMeat(Meat meat) {
        this.meat = meat;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
