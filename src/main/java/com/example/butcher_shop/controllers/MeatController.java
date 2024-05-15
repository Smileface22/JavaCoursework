package com.example.butcher_shop.controllers;

import com.example.butcher_shop.databases.*;
import com.example.butcher_shop.repositories.MeatRepository;
import com.example.butcher_shop.services.OrderItemService;
import com.example.butcher_shop.services.OrderService;
import com.example.butcher_shop.services.UserService;
import io.micrometer.common.util.internal.logging.InternalLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MeatController {

    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private MeatRepository meatRepository;
    private NumberOfItems numberOfItems = new NumberOfItems(1);

    @Autowired
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UserService.class);
    @Autowired
    public MeatController(UserService userService, OrderService orderService,OrderItemService orderItemService ) {
        this.userService = userService;
        this.orderService = orderService;
        this.orderItemService = orderItemService;
    }
    @GetMapping("/")
    public String home(Model model) {
        Iterable<Meat> meats = meatRepository.findAll();
        model.addAttribute("numberOfItems", numberOfItems);
        model.addAttribute("meats",meats);
        return "home";
    }
    @PostMapping("/shoppingCartPost")
    public String getNumberOfItems(@ModelAttribute NumberOfItems numberOfItems) {
        this.numberOfItems = numberOfItems;
        return "redirect:/";
    }
    @GetMapping("/basket")
    public String getBasket(Model model) {
        Order order = new Order();
        List<OrderItem> orderItems = new ArrayList<>();
        order.setOrderItems(orderItems);
        for (int i = 0; i < numberOfItems.getNumber(); i++) {
            OrderItem orderItem = new OrderItem();
            orderItem.setMeat(new Meat());
            order.addOrderItem(orderItem);
        }
        model.addAttribute("numberOfItems", numberOfItems);
        model.addAttribute("order", order);
        return "basket";
    }
    @PostMapping("/add-order")
    public String addNewOrder(@ModelAttribute Order order, @ModelAttribute OrderItem orderItems,
                              @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        order.setOrderDate(currentDate.format(formatter));
        order.setUser(customUserDetails.getUser());
        order.setStatus("оформлен");
        orderService.create(order);
        for (OrderItem orderItem : order.getOrderItems()) {
            orderItem.setOrder(order);
            orderItemService.saveOrderItem(orderItem);
        }
        //orderService.create(order);
        return "redirect:/account";
    }

    @GetMapping("/account")
    public String getAccount(Model model , @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        Long id = customUserDetails.getUser().getId();
        model.addAttribute("numberOfItems", numberOfItems);
        model.addAttribute("user", userService.findUserById(id));
        model.addAttribute("orders", orderService.getOrdersByUserId(id));
        return "account";
    }

}
