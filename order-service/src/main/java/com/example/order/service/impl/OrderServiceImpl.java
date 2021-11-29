package com.example.order.service.impl;

import com.example.order.mapper.OrderMapper;
import com.example.order.pojo.Order;
import com.example.order.pojo.User;
import com.example.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Order queryById(Long id) {
        // 从数据库中按ID查询订单详细信息
        Order order = orderMapper.selectById(id);
        // 获取订单的用户ID
        // 拼装获取用户信息的url
        String url = "http://localhost:9002/user/" + order.getUserId();
        // 通过远程调用获取用户信息
        User user = restTemplate.getForObject(url, User.class);
        // 将用户的信息封装到订单对象并返回
        order.setUser(user);
        return order;
    }
}
