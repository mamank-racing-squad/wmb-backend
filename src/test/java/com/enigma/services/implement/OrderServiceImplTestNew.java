package com.enigma.services.implement;

import com.enigma.entities.*;
import com.enigma.repositories.OrderRepository;
import com.enigma.services.DiningTableService;
import com.enigma.services.MenuCategoryService;
import com.enigma.services.MenuService;
import com.enigma.services.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@ExtendWith(SpringExtension.class)
//@SpringBootTest
class OrderServiceImplTestNew {
    @Autowired
    OrderService orderService;

    @MockBean
    OrderRepository orderRepository;
    @MockBean
    MenuCategoryService menuCategoryService;
    @MockBean
    MenuService menuService;
    @MockBean
    DiningTableService diningTableService;

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

    private DiningTable saveTable(){
        DiningTable diningTable = new DiningTable("A02", 2);
        return diningTableService.saveDiningTable(diningTable);
    }
    private LocalDateTime localDateTime = LocalDateTime.now();

    @Test
    void ordering_should_return_Order_when_Ordering() {
        MenuCategory menuCategory = new MenuCategory("Foods");
        MenuCategory newMenuCategory = menuCategoryService.createMenuCategory(menuCategory);
        Menu menu1 = new Menu("Ikan Bakar", new BigDecimal(50000), true, newMenuCategory.getIdMenuCategory());
        menu1 = menuService.createMenu(menu1);
        List<OrderDetail> orderDetails = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail(menu1.getIdMenu(),2,"pedas banget");
        orderDetails.add(orderDetail);
        Order order1 = new Order("Dadang",2, localDateTime, new BigDecimal(0), new BigDecimal(0), orderDetails, saveTable().getIdDiningTable());
        order1 = orderService.ordering(order1);
        Order expected = orderRepository.findById(order1.getIdOrder()).get();
        Order actual = orderService.getOrderById(order1.getIdOrder());
        assertEquals(expected, actual);
    }

    @Test
    void getOrderById() {
    }

    @Test
    void payment() {
    }

    @Test
    void getListOfOrder() {
    }

    @Test
    void getUnpaidOrder() {
    }
}