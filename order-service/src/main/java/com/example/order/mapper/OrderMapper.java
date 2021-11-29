package com.example.order.mapper;

import com.example.order.pojo.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {
    Order selectById(Long id);
}
