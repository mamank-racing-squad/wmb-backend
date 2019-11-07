package com.enigma.services.implement;

import com.enigma.entities.DiningTable;
import com.enigma.entities.Order;
import com.enigma.repositories.OrderRepository;
import com.enigma.services.DiningTableService;
import com.enigma.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    DiningTableService diningTableService;

    @Override
    public Order createOrder(Order order) {
        DiningTable diningTable = diningTableService.getDiningTableById(order.getIdDiningTable());
        diningTable.setStatus(false);
        order.setDiningTable(diningTable);
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getListOfOrder() {
        return orderRepository.findAll();
    }
}
