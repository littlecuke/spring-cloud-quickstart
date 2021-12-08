package org.example.order.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.example.feign.pojo.User;

@Data
@Alias("Order")
public class Order {
    private Long id;
    private String name;
    private String price;
    private Integer num;
    private Long userId;
    private User user;
}
