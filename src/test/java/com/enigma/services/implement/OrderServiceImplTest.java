package com.enigma.services.implement;

import com.enigma.entities.*;
import com.enigma.exceptions.ForbiddenException;
import com.enigma.exceptions.NotFoundException;
import com.enigma.repositories.*;
import com.enigma.services.DiningTableService;
import com.enigma.services.MenuCategoryService;
import com.enigma.services.MenuService;
import com.enigma.services.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
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
    private List<OrderDetail> initiateOrderDetails2() {
        MenuCategory menuCategory = new MenuCategory("Drinks");
        MenuCategory newMenuCategory = menuCategoryService.createMenuCategory(menuCategory);
        Menu menu1 = new Menu("Ice Tea", new BigDecimal(50000), newMenuCategory.getIdMenuCategory());
        menu1 = menuRepository.save(menu1);
        List<OrderDetail> orderDetails = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail(menu1.getIdMenu(),2);
        orderDetails.add(orderDetail);
        return orderDetails;
    }

    private LocalDateTime localDateTime = LocalDateTime.now();
    private static DiningTable table1 = new DiningTable("A03", 2);
    private static DiningTable table2 = new DiningTable("A02", 4);

    @Test
    public void getOrderById_should_returnTrue_Order_when_Found() {
        DiningTable diningTable = diningTableService.createDiningTable(table1);
        Order order = new Order("Dadang",2, localDateTime, initiateOrderDetails(), diningTable.getIdDiningTable(), "nothing", false);
        order = orderService.ordering(order);
        Order expected = orderService.getOrderById(order.getIdOrder());
        assertEquals(expected, order);
    }

    @Test
    public void getOrderById_should_return_NotFoundException_when_not_Found() {
        Assertions.assertThrows(NotFoundException.class, () -> {orderService.getOrderById("1dy4n9t1d4ck4d4");});
    }

    @Test
    public void ordering_should_return_Order_when_Ordering() {
        DiningTable diningTable = diningTableService.createDiningTable(table1);
        Order order1 = new Order("Dadang",2, localDateTime, initiateOrderDetails(), diningTable.getIdDiningTable(), "nothing", false);
        order1 = orderService.ordering(order1);
        Order expected = orderRepository.findById(order1.getIdOrder()).get();
        Order actual = orderService.getOrderById(order1.getIdOrder());
        assertEquals(expected, actual);
    }

    @Test
    public void getListOfOrder_should_return_2_when_2_dataInput() {
        DiningTable diningTable1 = diningTableService.createDiningTable(table1);
        DiningTable diningTable2 = diningTableService.createDiningTable(table2);

        Order order1 = new Order("Dadang",2, localDateTime, initiateOrderDetails(), diningTable1.getIdDiningTable(), "", false);
        Order order2 = new Order("Dudung",2, localDateTime, initiateOrderDetails2(), diningTable2.getIdDiningTable(), "", false);
        orderService.ordering(order1);
        orderService.ordering(order2);
        assertEquals(2, orderService.getListOfOrder().size());

    }

    @Test
    public void ordering_shouldFail_when_ordering_inUnavailable_DiningTable(){
        DiningTable diningTable1 = diningTableService.createDiningTable(table1);
        Order order1 = new Order("Dadang",2, localDateTime, initiateOrderDetails(), diningTable1.getIdDiningTable(), "", false);
        orderService.ordering(order1);
        Order order2 = new Order("Ujang",2, localDateTime, initiateOrderDetails2(), diningTable1.getIdDiningTable(), "", false);
        Assertions.assertThrows(ForbiddenException.class, () -> {orderService.ordering(order2);});
    }

    @Test
    public void ordering_shouldFail_when_totalCostumer_areNotAccordingTo_DiningTableCapacity(){
        DiningTable diningTable1 = diningTableService.createDiningTable(table1);
        Order order1 = new Order("Dadang",10, localDateTime, initiateOrderDetails(), diningTable1.getIdDiningTable(), "", false);
        Assertions.assertThrows(ForbiddenException.class, () -> {orderService.ordering(order1);});
    }

    @Test
    public void getUnpaidOrder_should_return_1_when_1_data_input(){
        DiningTable diningTable = diningTableService.createDiningTable(table1);
        Order order1 = new Order("Dadang",2, localDateTime, initiateOrderDetails(), diningTable.getIdDiningTable(), "nothing", false);
        orderService.ordering(order1);
        assertEquals(1, orderService.getUnpaidOrder().size());
    }

}