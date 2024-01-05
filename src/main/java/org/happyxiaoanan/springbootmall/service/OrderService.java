package org.happyxiaoanan.springbootmall.service;

import org.happyxiaoanan.springbootmall.dto.CreateOrderRequest;
import org.happyxiaoanan.springbootmall.dto.OrderQueryParams;
import org.happyxiaoanan.springbootmall.model.Order;

import java.util.List;

public interface OrderService {

    Integer countOrder(OrderQueryParams orderQueryParams);

    List<Order> getOrders(OrderQueryParams orderQueryParams);

    Order getOrderById(Integer orderId);

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
