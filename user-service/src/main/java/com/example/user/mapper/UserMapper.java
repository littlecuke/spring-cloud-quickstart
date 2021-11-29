package com.example.user.mapper;

import com.example.user.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    User selectById(Long id);
}
