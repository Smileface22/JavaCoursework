package com.example.butcher_shop.databases;

import jakarta.persistence.*;

@Entity
@Table(name = "meats")
public class Meat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;
    private String description;


}
