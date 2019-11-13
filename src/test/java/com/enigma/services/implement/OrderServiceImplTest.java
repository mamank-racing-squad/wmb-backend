package com.enigma.services.implement;

import com.enigma.entities.*;
import com.enigma.exceptions.NotAccordingToCapacityException;
import com.enigma.exceptions.PaymentUnsuccessfulException;
import com.enigma.exceptions.TableIsNotEmptyException;
import com.enigma.repositories.OrderDetailRepository;
import com.enigma.repositories.OrderRepository;
import com.enigma.services.DiningTableService;
import com.enigma.services.MenuCategoryService;
import com.enigma.services.MenuService;
import com.enigma.services.OrderService;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class OrderServiceImplTest {

    @Autowired
    OrderService orderService;

    @Autowired
    DiningTableService diningTableService;

    @Autowired
    MenuService menuService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    MenuCategoryService menuCategoryService;

    //sementara
    @Autowired
    OrderDetailRepository orderDetailRepository;

    @BeforeEach
    public void cleanUp() {
        orderDetailRepository.deleteAll();
        orderRepository.deleteAll();
    }

    public List<OrderDetail> initiateOrderDetails() {
        MenuCategory menuCategory = new MenuCategory("Foods");
        MenuCategory newMenuCategory = menuCategoryService.createMenuCategory(menuCategory);
        Menu menu1 = new Menu("Ikan Bakar", new BigDecimal(50000), true, newMenuCategory.getIdMenuCategory());
        menu1 = menuService.createMenu(menu1);
        List<OrderDetail> orderDetails = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail(menu1.getIdMenu(),2,"pedas banget");
        orderDetails.add(orderDetail);
        return orderDetails;
    }

    public DiningTable saveTable(){
        DiningTable diningTable = new DiningTable("A02", 2);
        return diningTableService.saveDiningTable(diningTable);
    }

    private LocalDateTime localDateTime = LocalDateTime.now();

    @Test
    void ordering_should_return_Order_when_Ordering() {
        Order order1 = new Order("Dadang",2, localDateTime, new BigDecimal(0), new BigDecimal(0), initiateOrderDetails(), saveTable().getIdDiningTable());
        order1 = orderService.ordering(order1);
        Order expected = orderRepository.findById(order1.getIdOrder()).get();
        Order actual = orderService.getOrderById(order1.getIdOrder());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getOrderById_should_returnTrue_Order_when_Found() {
        DiningTable newDiningTable = saveTable();
        Order order = new Order("Dadang",2, localDateTime, new BigDecimal(0), new BigDecimal(0), initiateOrderDetails(), newDiningTable.getIdDiningTable());
        order = orderService.ordering(order);
        Order expected = orderService.getOrderById(order.getIdOrder());
        Assertions.assertEquals(expected, order);
    }

    @Test
    void payment_should_return_Order_when_payment() {
        DiningTable newDiningTable = saveTable();
        Order order = new Order("Dadang",2, localDateTime, new BigDecimal(0), new BigDecimal(0), initiateOrderDetails(), newDiningTable.getIdDiningTable());
        Payment payment = new Payment(new BigDecimal(100000));
        Order expected = orderService.ordering(order);
        Order paidOrder = orderService.payment(order, payment);
        Assertions.assertEquals(orderService.getOrderById(expected.getIdOrder()), paidOrder);
    }

    @Test
    void payment_should_return_exception_when_paymentFail() {
        DiningTable newDiningTable = saveTable();
        Order order = new Order("Dadang",2, localDateTime, new BigDecimal(0), new BigDecimal(0), initiateOrderDetails(), newDiningTable.getIdDiningTable());
        Payment payment = new Payment(new BigDecimal(100));
        orderService.ordering(order);
        Assertions.assertThrows(PaymentUnsuccessfulException.class, () -> {Order paidOrder = orderService.payment(order, payment);});
    }

    @Test
    void getListOfOrder_should_return_2_when_2_dataInput() {
        DiningTable diningTable = new DiningTable("A03", 2);
        diningTable = diningTableService.saveDiningTable(diningTable);
        Order order1 = new Order("Dadang",2, localDateTime, new BigDecimal(0), new BigDecimal(0), initiateOrderDetails(), saveTable().getIdDiningTable());
        Order order2 = new Order("Dudung",2, localDateTime, new BigDecimal(0), new BigDecimal(0), initiateOrderDetails(), diningTable.getIdDiningTable());
        orderService.ordering(order1);
        orderService.ordering(order2);
        Assertions.assertEquals(2, orderService.getListOfOrder().size());

    }

    @Test
    void ordering_shouldFail_when_ordering_inUnavailable_DiningTable(){
        DiningTable newDiningTable = new DiningTable("A03", 2);
        newDiningTable = diningTableService.saveDiningTable(newDiningTable);
        Order order1 = new Order("Dadang",2, localDateTime, new BigDecimal(0), new BigDecimal(0), initiateOrderDetails(), newDiningTable.getIdDiningTable());
        orderService.ordering(order1);
        Order order2 = new Order("Ujang",2, localDateTime, new BigDecimal(0), new BigDecimal(0), initiateOrderDetails(), newDiningTable.getIdDiningTable());
        Assertions.assertThrows(TableIsNotEmptyException.class, () -> {orderService.ordering(order2);});

    }

    @Test
    void ordering_shouldFail_when_totalCostumer_areNotAccordingTo_DiningTableCapacity(){
        Order order1 = new Order("Dadang",10, localDateTime, new BigDecimal(0), new BigDecimal(0), initiateOrderDetails(), saveTable().getIdDiningTable());
        Assertions.assertThrows(NotAccordingToCapacityException.class, () -> {orderService.ordering(order1);});
    }
}