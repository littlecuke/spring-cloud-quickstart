package org.example.order.service.impl;

import org.example.order.clients.UserClient;
import org.example.order.mapper.OrderMapper;
import org.example.order.pojo.Order;
import org.example.order.pojo.User;
import org.example.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.ClientInfoStatus;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserClient userClient;

    @Override
    public Order queryById(Long id) {
        // 从数据库中按ID查询订单详细信息
        Order order = orderMapper.selectById(id);
        // 获取订单的用户ID
        // 拼装获取用户信息的url
        // 这里采用硬编码的方式完成url的拼接, 不方便部署
        // String url = "http://localhost:9002/user/" + order.getUserId();
        // 采用服务的名称代替ip地址和端口
         String url = "http://userservice/user/" + order.getUserId();
        // 通过远程调用获取用户信息
         User user = restTemplate.getForObject(url, User.class);
        // 将用户的信息封装到订单对象并返回
         order.setUser(user);
        return order;
    }

    @Override
    public Order queryByIdFromClient(Long id) {
        // 从数据库中按ID查询订单详细信息
        Order order = orderMapper.selectById(id);
        // 通过远程调用获取用户信息
        User user = userClient.findById(order.getUserId());
        order.setUser(user);
        return order;
    }
}
