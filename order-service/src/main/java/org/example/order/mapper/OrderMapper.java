package org.example.order.mapper;

import org.example.order.pojo.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {
    Order selectById(Long id);
}
