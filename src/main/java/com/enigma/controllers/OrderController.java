package com.enigma.controllers;

import com.enigma.entities.Order;
import com.enigma.entities.Payment;
import com.enigma.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class OrderController {

    @Autowired
    OrderService orderService;

    @PutMapping("/order")
    public Order ordering(@RequestBody Order order){
        return orderService.ordering(order);
    }

    @PutMapping("/payment/{id}")
    public Order payment(@PathVariable String id, @RequestBody Payment payment){
        Order order = orderService.getOrderById(id);
        return orderService.payment(order, payment);
    }

    @GetMapping("/payment")
    public List<Order> getUnpaidOrder(){
        return orderService.getUnpaidOrder();
    }

    @GetMapping("/orders")
    public List<Order> getListOfOrder(){
        return orderService.getListOfOrder();
    }

    @GetMapping("/orders/{id}")
    public Order getOrderById(@PathVariable String id){
        return orderService.getOrderById(id);
    }
}
