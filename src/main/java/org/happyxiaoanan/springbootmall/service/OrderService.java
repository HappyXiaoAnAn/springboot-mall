package org.happyxiaoanan.springbootmall.service;

import org.happyxiaoanan.springbootmall.dto.CreateOrderRequest;
import org.happyxiaoanan.springbootmall.model.Order;

public interface OrderService {

    Order getOrderById(Integer orderId);

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
