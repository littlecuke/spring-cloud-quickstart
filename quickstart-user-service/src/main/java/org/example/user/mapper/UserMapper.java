package org.example.user.mapper;

import org.example.user.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    User selectById(Long id);
}
