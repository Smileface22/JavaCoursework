package com.example.butcher_shop.services;

import com.example.butcher_shop.databases.Order;
import com.example.butcher_shop.databases.OrderItem;
import com.example.butcher_shop.repositories.OrderItemRepository;
import com.example.butcher_shop.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private OrderItemRepository orderItemRepository;


    @Autowired
    public OrderService(OrderRepository orderRepository,OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }
    public Iterable<Order> getOrdersByUserId(long userId) {
        return orderRepository.findAllByUserId(userId);
    }
    public void create(Order order) {
        orderRepository.save(order);
    }
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    public void saveOrderItem(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }
}
