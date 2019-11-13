package com.enigma.services.implement;

import com.enigma.entities.*;
import com.enigma.exceptions.ForbiddenException;
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
        DiningTable diningTable = diningTableService.getDiningTableById(order.getIdDiningTable());
        order.setDiningTable(diningTable);
        diningTableService.costumerDining(order.getTotalCostumer(), diningTable);
        BigDecimal totalPrice = getTotalPrice(order);
        order.setTotalPrice(totalPrice);
        LocalDateTime localDateTime = LocalDateTime.now();
        order.setCreateAt(localDateTime);
        return orderRepository.save(order);
    }

    private BigDecimal getTotalPrice(Order order) {
        BigDecimal totalPrice = new BigDecimal(0);
        for(OrderDetail orderDetail: order.getOrderDetails()){
            Menu menu = menuService.getMenuById(orderDetail.getIdMenuTransient());
            orderDetail.setIdOrder(order);
            orderDetail.setIdMenu(menu);
            orderDetail.setSubTotalPrice(menu.getPrice().multiply(new BigDecimal(orderDetail.getAmount())));
            totalPrice = totalPrice.add(orderDetail.getSubTotalPrice());
        }
        return totalPrice;
    }

    @Override
    public Order getOrderById(String id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public Order payment(Order order, Payment payment) {
        Order willBePaidOrder = getOrderById(order.getIdOrder());
        DiningTable diningTable = order.getDiningTable();
        diningTable.costumerOut();
        willBePaidOrder.setPayment(payment.getPay());
        if(willBePaidOrder.getPayment().compareTo(willBePaidOrder.getTotalPrice()) < 0){
            throw new ForbiddenException("Sorry, the money you entered is not enough.");
        }
        willBePaidOrder.setChange(willBePaidOrder.getPayment().subtract(willBePaidOrder.getTotalPrice()));
        return orderRepository.save(willBePaidOrder);
    }

    @Override
    public List<Order> getListOfOrder() {
        return orderRepository.findAll();
    }
}
