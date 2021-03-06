package com.enigma.controllers;

import com.enigma.entities.*;
import com.enigma.repositories.MenuCategoryRepository;
import com.enigma.repositories.MenuRepository;
import com.enigma.repositories.OrderRepository;
import com.enigma.services.DiningTableService;
import com.enigma.services.MenuCategoryService;
import com.enigma.services.MenuService;
import com.enigma.services.OrderService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {

    @Autowired
    OrderService orderService;

    @Autowired
    DiningTableService diningTableService;

    @Autowired
    MenuService menuService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    MenuCategoryService menuCategoryService;
    @Autowired
    MenuRepository menuRepository;
    @Autowired
    MenuCategoryRepository menuCategoryRepository;
    @Before
    public void cleanUp(){
        menuRepository.deleteAll();
        menuCategoryRepository.deleteAll();
        orderRepository.deleteAll();
    }

    public List<OrderDetail> initiateOrderDetails() {
        MenuCategory menuCategory = new MenuCategory("Foods");
        MenuCategory newMenuCategory = menuCategoryService.createMenuCategory(menuCategory);
        Menu menu1 = new Menu("Ikan Bakar", new BigDecimal(50000), newMenuCategory.getIdMenuCategory());
        menu1 = menuService.createMenu(menu1);
        List<OrderDetail> orderDetails = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail(menu1.getIdMenu(),2);
        orderDetails.add(orderDetail);
        return orderDetails;
    }

    public DiningTable saveTable(){
        DiningTable diningTable = new DiningTable("A02", 2);
        return diningTableService.createDiningTable(diningTable);
    }

    private LocalDateTime localDateTime = LocalDateTime.now();
    @Test
    void getOrderById_should_returnTrue_Order_when_Found() throws Exception {
        Order order1 = new Order("Dadang", 2, localDateTime, initiateOrderDetails(), saveTable().getIdDiningTable(), "", false);
        orderRepository.save(order1);

        mockMvc.perform(get("/order/{id}", order1.getIdOrder())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idOrder").value(order1.getIdOrder()));
    }

    @Test
    void payment() {
    }

    @Test
    void getListOfOrder() {
    }
}