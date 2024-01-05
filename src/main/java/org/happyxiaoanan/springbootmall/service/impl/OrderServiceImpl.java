package org.happyxiaoanan.springbootmall.service.impl;

import org.happyxiaoanan.springbootmall.dao.OrderDao;
import org.happyxiaoanan.springbootmall.dao.ProductDao;
import org.happyxiaoanan.springbootmall.dao.UserDao;
import org.happyxiaoanan.springbootmall.dto.BuyItem;
import org.happyxiaoanan.springbootmall.dto.CreateOrderRequest;
import org.happyxiaoanan.springbootmall.model.Order;
import org.happyxiaoanan.springbootmall.model.OrderItem;
import org.happyxiaoanan.springbootmall.model.Product;
import org.happyxiaoanan.springbootmall.model.User;
import org.happyxiaoanan.springbootmall.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    private final static Logger log = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    @Override
    public Order getOrderById(Integer orderId) {
        Order order = orderDao.getOrderById(orderId);

        List<OrderItem> orderItemList = orderDao.getOrderItemsByOrderId(orderId);

        order.setOrderItemList(orderItemList);

        return order;
    }

    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {
        // check if user exit
        User user = userDao.getUserById(userId);

        if (user == null) {
            log.warn("UserId: {} not exit!", userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();

        for (BuyItem buyItem : createOrderRequest.getBuyItemList()) {
            Product product = productDao.getProductById(buyItem.getProductId());

            // check if product stock is enough
            if (product == null) {
                log.warn("Product: {} not exit!", buyItem.getProductId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            } else if (product.getStock() < buyItem.getQuantity()) {
                log.warn("Product: {} is not enough. Remain: {}, Request: {}",
                        product.getProductId(), product.getStock(), buyItem.getQuantity());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

            // update product stock
            productDao.updateStock(product.getProductId(), product.getStock() - buyItem.getQuantity());

            // calculate total price
            int amount = buyItem.getQuantity() * product.getPrice();
            totalAmount = totalAmount + amount;

            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);

            orderItemList.add(orderItem);
        }

        // crete order
        Integer orderId = orderDao.createOrder(userId, totalAmount);

        orderDao.createOrderItems(orderId, orderItemList);

        return orderId;
    }
}
