package com.enigma.controllers;

import com.enigma.entities.Order;
import com.enigma.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@CrossOrigin
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/order")
    public Order ordering(@RequestBody Order order){
        return orderService.ordering(order);
    }

    @PostMapping("/payment/{id}")
    public Order payment(@PathVariable String id, @RequestBody BigDecimal payment){
        Order order = orderService.getOrderById(id);
        return orderService.payment(order, payment);
    }

    @GetMapping("/orders")
    public List<Order> getListOfOrder(){
        return orderService.getListOfOrder();
    }
}
