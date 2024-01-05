package org.happyxiaoanan.springbootmall.dao;

import org.happyxiaoanan.springbootmall.dto.OrderQueryParams;
import org.happyxiaoanan.springbootmall.model.Order;
import org.happyxiaoanan.springbootmall.model.OrderItem;

import java.util.List;

public interface OrderDao {

    Integer countOrder(OrderQueryParams orderQueryParams);

    List<Order> getOrders(OrderQueryParams orderQueryParams);

    Order getOrderById(Integer orderId);

    List<OrderItem> getOrderItemsByOrderId(Integer orderId);

    Integer createOrder(Integer userId, Integer totalAmount);

    void createOrderItems(Integer orderId, List<OrderItem> orderItemList);
}
