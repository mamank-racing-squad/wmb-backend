package com.enigma.services.implement;

import com.enigma.entities.Order;
import com.enigma.repositories.OrderRepository;
import com.enigma.services.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class OrderServiceImplTest {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    void ordering_should_return_Order_when_Ordering() {
//        Order order1 = new Order();
//        order1 = orderService.ordering(order1);
//        Order expected = orderRepository.findById(order1.getIdOrder()).get();
//        assertEquals(expected, order1);

    }

    @Test
    void getOrderById_should_returnOneOrder_when_Found() {
    }

    @Test
    void payment_should_return_Order_when_payment() {
    }

    @Test
    void getListOfOrder_should_return_2_when_2_dataInput() {
//        Order order1 = new Order();
//        Order order2 = new Order();
//
//        orderService.ordering(order1);
//        orderService.ordering(order2);
//
//        assertEquals(2, orderService.getListOfOrder().size());

    }
}