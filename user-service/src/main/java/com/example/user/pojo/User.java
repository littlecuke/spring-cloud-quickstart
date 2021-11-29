package com.example.user.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("User")
public class User {
    private Long id;
    private String username;
    private String address;
}
