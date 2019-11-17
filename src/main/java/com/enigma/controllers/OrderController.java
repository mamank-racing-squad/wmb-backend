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

    @PutMapping("/order")
    public Order ordering(@RequestBody Order order){
        return orderService.ordering(order);
    }

    @GetMapping("/unpaid-order")
    public List<Order> getUnpaidOrder(){
        return orderService.getUnpaidOrder();
    }

    @GetMapping("/orders")
    public List<Order> getListOfOrder(){
        return orderService.getListOfOrder();
    }


    @GetMapping("/order/{id}")
    public Order getOrderById(@PathVariable String id){
        return orderService.getOrderById(id);
    }

    @GetMapping("/reports")
    public BigDecimal getTotalIncome(){
        return orderService.getTotalIncome();
    }
}
