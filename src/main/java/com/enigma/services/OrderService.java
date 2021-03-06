package com.enigma.services;

import com.enigma.entities.Order;
import com.enigma.entities.Payment;
import org.aspectj.weaver.ast.Or;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    Order ordering(Order order);
    Order getOrderById(String id);
    List<Order> getListOfOrder();
    List<Order> getUnpaidOrder();
    BigDecimal getTotalIncome();
}
