package com.example.butcher_shop.repositories;

import com.example.butcher_shop.databases.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Iterable<Order> findAllByUserId(long userId);
}
