package org.happyxiaoanan.springbootmall.service;

import org.happyxiaoanan.springbootmall.dto.CreateOrderRequest;

public interface OrderService {

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
