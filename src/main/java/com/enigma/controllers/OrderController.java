package com.enigma.controllers;

import com.enigma.entities.Order;
import com.enigma.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/order")
    public Order createOrder(@RequestBody Order order){
        return orderService.createOrder(order);
    }

    @GetMapping("/orders")
    public List<Order> getListOfOrder(){
        return orderService.getListOfOrder();
    }
}
