package com.enigma.services.implement;

import com.enigma.entities.*;
import com.enigma.repositories.*;
import com.enigma.services.DiningTableService;
import com.enigma.services.MenuCategoryService;
import com.enigma.services.MenuService;
import com.enigma.services.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

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
    @Autowired
    MenuCategoryRepository menuCategoryRepository;
    @Autowired
    MenuRepository menuRepository;
    @Autowired
    DiningTableRepository diningTableRepository;

    @Before
    public void cleanUp() {
        orderDetailRepository.deleteAll();
        orderRepository.deleteAll();
//        menuCategoryRepository.deleteAll();
//        menuRepository.deleteAll();
        diningTableRepository.deleteAll();
    }
//    private List<OrderDetail> initiateOrderDetails() {
//        MenuCategory menuCategory = new MenuCategory("Foods");
//        MenuCategory newMenuCategory = menuCategoryService.createMenuCategory(menuCategory);
//        Menu menu1 = new Menu("Ikan Bakar", new BigDecimal(50000), true, newMenuCategory.getIdMenuCategory());
//        menu1 = menuService.createMenu(menu1);
//        List<OrderDetail> orderDetails = new ArrayList<>();
//        OrderDetail orderDetail = new OrderDetail(menu1.getIdMenu(),2,"pedas banget");
//        orderDetails.add(orderDetail);
//        return orderDetails;
//    }
//
//    private DiningTable saveTable(){
//
//        return diningTableService.saveDiningTable(diningTable);
//    }


    private LocalDateTime localDateTime = LocalDateTime.now();
    private static DiningTable table1 = new DiningTable("A03", 2);
    private static DiningTable table2 = new DiningTable("A02", 4);

    @Test
    public void getOrderById_should_returnTrue_Order_when_Found() {
        MenuCategory menuCategory = new MenuCategory("Foods");
        MenuCategory newMenuCategory = menuCategoryService.createMenuCategory(menuCategory);
        Menu menu1 = new Menu("Ikan Bakar", new BigDecimal(50000), true, newMenuCategory.getIdMenuCategory());
        menu1 = menuService.createMenu(menu1);
        List<OrderDetail> orderDetails = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail(menu1.getIdMenu(),2,"pedas banget");
        orderDetails.add(orderDetail);

        DiningTable diningTable = diningTableService.createDiningTable(table1);

        diningTable = diningTableService.createDiningTable(diningTable);
        Order order = new Order("Dadang",2, localDateTime, new BigDecimal(0), new BigDecimal(0), orderDetails, diningTable.getIdDiningTable());
        order = orderService.ordering(order);
        Order expected = orderService.getOrderById(order.getIdOrder());
        assertEquals(expected, order);
    }

    @Test
    public void ordering_should_return_Order_when_Ordering() {
        MenuCategory menuCategory = new MenuCategory("drinks");
        MenuCategory newMenuCategory = menuCategoryService.createMenuCategory(menuCategory);
        Menu menu1 = new Menu("lauk Bakar", new BigDecimal(50000), true, newMenuCategory.getIdMenuCategory());
        menu1 = menuService.createMenu(menu1);
        List<OrderDetail> orderDetails = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail(menu1.getIdMenu(),2,"pedas banget");
        orderDetails.add(orderDetail);
        DiningTable diningTable = diningTableService.createDiningTable(table1);
        Order order1 = new Order("Dadang",2, localDateTime, new BigDecimal(0), new BigDecimal(0), orderDetails, diningTable.getIdDiningTable());
        order1 = orderService.ordering(order1);
        Order expected = orderRepository.findById(order1.getIdOrder()).get();
        Order actual = orderService.getOrderById(order1.getIdOrder());
        assertEquals(expected, actual);
    }


//
//    @Test
//    void payment_should_return_Order_when_payment() {
//        DiningTable newDiningTable = saveTable();
//        Order order = new Order("Dadang",2, localDateTime, new BigDecimal(0), new BigDecimal(0), initiateOrderDetails(), newDiningTable.getIdDiningTable());
//        Payment payment = new Payment(new BigDecimal(100000));
//        Order expected = orderService.ordering(order);
//        Order paidOrder = orderService.payment(order, payment);
//        assertEquals(orderService.getOrderById(expected.getIdOrder()), paidOrder);
//    }
//
//    @Test
//    void payment_should_return_exception_when_paymentFail() {
//        DiningTable newDiningTable = saveTable();
//        Order order = new Order("Dadang",2, localDateTime, new BigDecimal(0), new BigDecimal(0), initiateOrderDetails(), newDiningTable.getIdDiningTable());
//        Payment payment = new Payment(new BigDecimal(100));
//        orderService.ordering(order);
//        Assertions.assertThrows(ForbiddenException.class, () -> {Order paidOrder = orderService.payment(order, payment);});
//    }
//
//    @Test
//    void getListOfOrder_should_return_2_when_2_dataInput() {
//        DiningTable diningTable = new DiningTable("A03", 2);
//        diningTable = diningTableService.saveDiningTable(diningTable);
//        Order order1 = new Order("Dadang",2, localDateTime, new BigDecimal(0), new BigDecimal(0), initiateOrderDetails(), saveTable().getIdDiningTable());
//        Order order2 = new Order("Dudung",2, localDateTime, new BigDecimal(0), new BigDecimal(0), initiateOrderDetails(), diningTable.getIdDiningTable());
//        orderService.ordering(order1);
//        orderService.ordering(order2);
//        assertEquals(2, orderService.getListOfOrder().size());
//
//    }
//
//    @Test
//    void ordering_shouldFail_when_ordering_inUnavailable_DiningTable(){
//        DiningTable newDiningTable = new DiningTable("A03", 2);
//        newDiningTable = diningTableService.saveDiningTable(newDiningTable);
//        Order order1 = new Order("Dadang",2, localDateTime, new BigDecimal(0), new BigDecimal(0), initiateOrderDetails(), newDiningTable.getIdDiningTable());
//        orderService.ordering(order1);
//        Order order2 = new Order("Ujang",2, localDateTime, new BigDecimal(0), new BigDecimal(0), initiateOrderDetails(), newDiningTable.getIdDiningTable());
//        Assertions.assertThrows(ForbiddenException.class, () -> {orderService.ordering(order2);});
//
//    }
//
//    @Test
//    void ordering_shouldFail_when_totalCostumer_areNotAccordingTo_DiningTableCapacity(){
//        Order order1 = new Order("Dadang",10, localDateTime, new BigDecimal(0), new BigDecimal(0), initiateOrderDetails(), saveTable().getIdDiningTable());
//        Assertions.assertThrows(ForbiddenException.class, () -> {orderService.ordering(order1);});
//    }
}