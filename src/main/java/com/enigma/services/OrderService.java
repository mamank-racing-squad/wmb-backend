package com.enigma.services;

import com.enigma.entities.Order;
import com.enigma.entities.Payment;
import org.aspectj.weaver.ast.Or;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    Order ordering(Order order);
    Order getOrderById(String id);
    Order payment(Order order, Payment payment);
    List<Order> getListOfOrder();
    List<Order> getUnpaidOrder();
}
