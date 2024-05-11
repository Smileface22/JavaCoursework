package com.example.butcher_shop.databases;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "meats")
public class Meat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;
    private String description;

    @OneToMany(mappedBy = "meat")
    private List<OrderItem> orderItem;
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
    public Meat(){}
}
