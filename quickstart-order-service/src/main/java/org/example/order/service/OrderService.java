package org.example.order.service;

import org.example.order.pojo.Order;

public interface OrderService {
    /**
     * 查询订单详细信息
     */
    Order queryById(Long id);

    /**
     * 查询订单详细信息，通过feign远程调用查询用户信息
     */
    Order queryByIdFromClient(Long id);
}
