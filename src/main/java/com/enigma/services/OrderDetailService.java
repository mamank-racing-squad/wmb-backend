package com.enigma.services;

import com.enigma.entities.OrderDetail;
import java.util.List;

public interface OrderDetailService {
    public OrderDetail getOrderDetailById(String id);
    public List<OrderDetail> getAllOrderDetail();
    public OrderDetail createOrderDetail(OrderDetail orderDetail);
    public void deleteOrderDetailById(String id);
    public OrderDetail updateOrderDetail(OrderDetail orderDetail);
}
