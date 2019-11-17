package com.enigma.services.implement;

import com.enigma.entities.DiningTable;
import com.enigma.entities.Order;
import com.enigma.entities.Payment;
import com.enigma.exceptions.ForbiddenException;
import com.enigma.exceptions.NotFoundException;
import com.enigma.repositories.PaymentRepository;
import com.enigma.services.OrderService;
import com.enigma.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    OrderService orderService;

    @Override
    public Payment payment(Payment payment) {
        Order toBePaidOrder = orderService.getOrderById(payment.getIdOrder());
        DiningTable diningTable = toBePaidOrder.getDiningTable();
        diningTable.costumerOut();
        toBePaidOrder.paid();
        if(payment.getPay().compareTo(toBePaidOrder.getTotalPrice()) < 0){
            throw new ForbiddenException("Sorry, the money you entered is not enough.");
        }
        payment.setChange(payment.getPay().subtract(toBePaidOrder.getTotalPrice()));
        payment.setOrder(toBePaidOrder);
        payment.setPaidAt(LocalDateTime.now());
        return paymentRepository.save(payment);
    }

    @Override
    public Payment getPaymentById(String id) {
        if (!(paymentRepository.findById(id).isPresent())) throw new NotFoundException("Payment with id : " + id + " is not found.");
        return paymentRepository.findById(id).get();
    }

    @Override
    public List<Payment> getAllPayment() {
        return paymentRepository.findAll();
    }
}
