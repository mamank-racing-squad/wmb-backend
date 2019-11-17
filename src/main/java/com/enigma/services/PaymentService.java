package com.enigma.services;

import com.enigma.entities.Order;
import com.enigma.entities.Payment;

import java.util.List;

public interface PaymentService {
    Payment payment(Payment payment);
    Payment getPaymentById(String id);
    List<Payment> getAllPayment();
}
