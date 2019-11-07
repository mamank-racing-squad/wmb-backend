package com.enigma.controllers;

import com.enigma.entities.OrderDetail;
import com.enigma.services.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class OrderDetailController {

    @Autowired
    OrderDetailService orderDetailService;

    @PostMapping("/order-detail")
    public OrderDetail createOrderDetail(@RequestBody OrderDetail orderDetail){
        return orderDetailService.createOrderDetail(orderDetail);
    }

    @GetMapping("/order-detail/{id}")
    public OrderDetail getOrderDetailById(@PathVariable String idOrder){
        return orderDetailService.getOrderDetailById(idOrder);
    }

    @GetMapping("/list-order-detail")
    public List<OrderDetail> getAllOrderDetail(){
        return orderDetailService.getAllOrderDetail();
    }

    @DeleteMapping("/order-detail/{id}")
    public void deleteOrderDetail(@PathVariable String id){
        orderDetailService.deleteOrderDetailById(id);
    }

    @PutMapping("/order-detail")
    public OrderDetail updateOrderDetail(@RequestBody OrderDetail orderDetail){
        return orderDetailService.updateOrderDetail(orderDetail);
    }

}
