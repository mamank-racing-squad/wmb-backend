package com.enigma.services.implement;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaymentServiceImplTest {

    @Test
    public void payment() {
    }

    @Test
    public void getPaymentById() {
    }

    @Test
    public void getAllPayment() {
    }

//    @Test
//    public void payment_should_return_Order_when_payment() {
//        DiningTable newDiningTable = diningTableService.createDiningTable(table1);
//        Order order = new Order("Dadang",2, localDateTime, new BigDecimal(0), new BigDecimal(0), initiateOrderDetails(), newDiningTable.getIdDiningTable(), "");
//        Payment payment = new Payment(new BigDecimal(100000));
//        Order expected = orderService.ordering(order);
//        Order paidOrder = orderService.payment(order, payment);
//        assertEquals(orderService.getOrderById(expected.getIdOrder()), paidOrder);
//    }

//    @Test
//    public void payment_should_return_exception_when_paymentFail() {
//        DiningTable diningTable = diningTableService.createDiningTable(table1);
//        Order order = new Order("Dadang",2, localDateTime, new BigDecimal(0), new BigDecimal(0), initiateOrderDetails(), diningTable.getIdDiningTable(), "");
//        Payment payment = new Payment(new BigDecimal(100));
//        orderService.ordering(order);
//        Assertions.assertThrows(ForbiddenException.class, () -> {orderService.payment(order, payment);});
//    }
}