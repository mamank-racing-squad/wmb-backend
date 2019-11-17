package com.enigma.services.implement;

import com.enigma.entities.*;
import com.enigma.exceptions.ForbiddenException;
import com.enigma.exceptions.NotFoundException;
import com.enigma.repositories.*;
import com.enigma.services.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaymentServiceImplTest {

    @Autowired
    PaymentService paymentService;

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    OrderService orderService;
    @Autowired
    MenuService menuService;
    @Autowired
    MenuCategoryService menuCategoryService;
    @Autowired
    DiningTableService diningTableService;
    @Autowired
    DiningTableRepository diningTableRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    MenuCategoryRepository menuCategoryRepository;
    @Autowired
    MenuRepository menuRepository;
    @Before
    public void cleanUp(){
        paymentRepository.deleteAll();
        orderRepository.deleteAll();
        diningTableRepository.deleteAll();
        menuRepository.deleteAll();
        menuCategoryRepository.deleteAll();
    }
    @After
    public void cleanUpAgain(){
        paymentRepository.deleteAll();
        orderRepository.deleteAll();
        diningTableRepository.deleteAll();
        menuRepository.deleteAll();
        menuCategoryRepository.deleteAll();
    }
    private LocalDateTime localDateTime = LocalDateTime.now();
    private static DiningTable table1 = new DiningTable("A03", 2);
    private static DiningTable table2 = new DiningTable("A02", 4);
    private static MenuCategory menuCategory1 = new MenuCategory("Foods");
    private static MenuCategory menuCategory2 = new MenuCategory("Beverage");


    private List<OrderDetail> initiateOrderDetails() {
        menuCategory1= menuCategoryService.createMenuCategory(menuCategory1);
        Menu menu1 = new Menu("Ikan Bakar", new BigDecimal(50000), menuCategory1.getIdMenuCategory());
        menu1 = menuService.createMenu(menu1);
        List<OrderDetail> orderDetails = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail(menu1.getIdMenu(),2);
        orderDetails.add(orderDetail);
        return orderDetails;
    }
    private List<OrderDetail> initiateOrderDetails2() {
        menuCategory2= menuCategoryService.createMenuCategory(menuCategory2);
        Menu menu1 = new Menu("Kopi", new BigDecimal(50000), menuCategory2.getIdMenuCategory());
        menu1 = menuService.createMenu(menu1);
        List<OrderDetail> orderDetails = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail(menu1.getIdMenu(),2);
        orderDetails.add(orderDetail);
        return orderDetails;
    }

    private Order initiateOrder(){
        table1 = diningTableService.createDiningTable(table1);
        Order order1 = new Order("Dadang",2, localDateTime, initiateOrderDetails(), table1.getIdDiningTable(), "nothing", false);
        return orderService.ordering(order1);
    }
    private Order initiateOrder2(){
        table2 = diningTableService.createDiningTable(table2);
        Order order1 = new Order("Dudung",2, localDateTime, initiateOrderDetails2(), table2.getIdDiningTable(), "nothing", false);
        return orderService.ordering(order1);
    }

    @Test
    public void payment_should_returnPayment_when_payment() {
        Payment payment = new Payment(new BigDecimal(100000), initiateOrder().getIdOrder());
        payment = paymentService.payment(payment);
        assertEquals(payment, paymentRepository.findById(payment.getIdPayment()).get());
    }

    @Test
    public void getPaymentById_Should_returnPayment_whenFound() {
        Payment payment = new Payment(new BigDecimal(100000), initiateOrder().getIdOrder());
        payment = paymentService.payment(payment);
        assertEquals(payment, paymentService.getPaymentById(payment.getIdPayment()));
    }

    @Test
    public void getAllPayment_should_return_2_payment_when2data_inputed() {
        Payment payment1 = new Payment(new BigDecimal(100000), initiateOrder().getIdOrder());
        Payment payment2 = new Payment(new BigDecimal(100000), initiateOrder2().getIdOrder());
        paymentService.payment(payment1);
        paymentService.payment(payment2);
        assertEquals(2,paymentService.getAllPayment().size());
    }

    @Test
    public void payment_should_return_exception_when_paymentFail() {
        Payment payment = new Payment(new BigDecimal(1000), initiateOrder().getIdOrder());
        Assertions.assertThrows(ForbiddenException.class, () -> {paymentService.payment(payment);});
    }

    @Test
    public void getPaymentById_should_return_NotFoundException_when_not_found() {
        Assertions.assertThrows(NotFoundException.class, () -> paymentService.getPaymentById("1dy4n9t1d4ck4d4"));
    }

    @Test
    public void getPaymentById_should_return_payment_when_found() {
        Payment payment = new Payment(new BigDecimal(100000), initiateOrder().getIdOrder());
        payment = paymentService.payment(payment);
        assertEquals(payment, paymentRepository.findById(payment.getIdPayment()).get());
    }

}