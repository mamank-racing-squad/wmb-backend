package com.enigma.services;

import com.enigma.entities.Order;

import java.util.List;

public interface OrderService {
    public Order createOrder(Order order);
    public List<Order> getListOfOrder();
}
