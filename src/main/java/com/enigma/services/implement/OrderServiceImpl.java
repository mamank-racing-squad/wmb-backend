package com.enigma.services.implement;

import com.enigma.entities.*;
import com.enigma.exceptions.ForbiddenException;
import com.enigma.exceptions.NotFoundException;
import com.enigma.repositories.OrderRepository;
import com.enigma.services.DiningTableService;
import com.enigma.services.MenuService;
import com.enigma.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    DiningTableService diningTableService;

    @Autowired
    MenuService menuService;

    @Override
    public Order ordering(Order order) {
        if (order.getOrderDetails().isEmpty()) throw new ForbiddenException("Menu is empty, please select menu");
        if (order.getCostumerName().isEmpty()) throw new ForbiddenException("Please input PIC Name");
        if (order.getTotalCostumer().equals(0)) throw new ForbiddenException("Please input total Costumer");
        DiningTable diningTable = diningTableService.getDiningTableById(order.getIdDiningTable());
        order.setDiningTable(diningTable);
        diningTableService.costumerDining(order.getTotalCostumer(), diningTable);
        BigDecimal totalPrice = getTotalPrice(order);
        order.setTotalPrice(totalPrice);
        LocalDateTime localDateTime = LocalDateTime.now();
        order.setCreateAt(localDateTime);
        order.dining();
        return orderRepository.save(order);
    }

    private BigDecimal getTotalPrice(Order order) {
        BigDecimal totalPrice = new BigDecimal(0);
        for(OrderDetail orderDetail: order.getOrderDetails()){
            Menu menu = menuService.getMenuById(orderDetail.getIdMenu());
            orderDetail.setIdOrder(order);
            orderDetail.setMenu(menu);
            orderDetail.setSubTotalPrice(menu.getPrice().multiply(new BigDecimal(orderDetail.getAmount())));
            totalPrice = totalPrice.add(orderDetail.getSubTotalPrice());
        }
        return totalPrice;
    }

    @Override
    public Order getOrderById(String id) {
        if (!(orderRepository.findById(id).isPresent())) throw new NotFoundException("Order with id : " + id + " is not found.");
        return orderRepository.findById(id).get();
    }

    @Override
    public List<Order> getListOfOrder() {
        return orderRepository.findAllByOrderByCreateAtDesc();
    }

    @Override
    public List<Order> getUnpaidOrder() {
        return orderRepository.findByIsPaidFalse();
    }

    @Override
    public BigDecimal getTotalIncome() {
        return orderRepository.sumTotalIncome();
    }
}
