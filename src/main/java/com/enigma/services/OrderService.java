package com.enigma.services;

import com.enigma.entities.Order;
import org.aspectj.weaver.ast.Or;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    public Order ordering(Order order);
    public Order getOrderById(String id);
    public Order payment(Order order, BigDecimal payment);
    public List<Order> getListOfOrder();
}
