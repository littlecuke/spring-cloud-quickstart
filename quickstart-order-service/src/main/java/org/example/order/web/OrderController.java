package org.example.order.web;

import org.example.order.pojo.Order;
import org.example.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 查询订单详情
     * <p>
     * url: /order/1
     *
     * @param id 订单编号
     * @return 订单详情
     */
    @GetMapping("/{id}")
    public Order queryById(@PathVariable("id") Long id) {
        return orderService.queryById(id);
    }

    /**
     * 通过feign远程调用获取用户信息
     */
    @GetMapping("/client/{id}")
    public Order queryByIdFromClient(@PathVariable("id") Long id) {
        return orderService.queryByIdFromClient(id);
    }

}
