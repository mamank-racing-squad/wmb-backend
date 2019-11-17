package com.enigma.entities;

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

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderTest {
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
        menuCategoryRepository.deleteAll();
        menuRepository.deleteAll();
        diningTableRepository.deleteAll();
    }

    private List<OrderDetail> initiateOrderDetails() {
        MenuCategory menuCategory = new MenuCategory("Foods");
        MenuCategory newMenuCategory = menuCategoryService.createMenuCategory(menuCategory);
        Menu menu1 = new Menu("Ikan Bakar", new BigDecimal(50000), newMenuCategory.getIdMenuCategory());
        menu1 = menuRepository.save(menu1);
        List<OrderDetail> orderDetails = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail(menu1.getIdMenu(),2);
        orderDetails.add(orderDetail);
        return orderDetails;
    }

    private LocalDateTime localDateTime = LocalDateTime.now();
    private static DiningTable table1 = new DiningTable("A03", 2);

    @Test
    public void dining_should_make_isPaid_toBe_false() {
        DiningTable diningTable1 = diningTableService.createDiningTable(table1);
        Order order1 = new Order("Dadang",10, localDateTime, initiateOrderDetails(), diningTable1.getIdDiningTable(), "", false);
        order1.dining();
        assertFalse(order1.getIsPaid());
    }

    @Test
    public void paid_should_make_isPaid_toBe_true() {
        DiningTable diningTable1 = diningTableService.createDiningTable(table1);
        Order order1 = new Order("Dadang",10, localDateTime, initiateOrderDetails(), diningTable1.getIdDiningTable(), "", false);
        order1.paid();
        assertTrue(order1.getIsPaid());
    }
}