package org.example.user.web;

import org.example.user.pojo.User;
import org.example.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询用户详细信息
     * <p>
     * url: /user/1
     *
     * @param id 用户id
     * @return 用户
     */
    @GetMapping("/{id}")
    public User queryById(@PathVariable("id") Long id) {
        return userService.queryById(id);
    }

    @GetMapping("/filter-header")
    public String filterForHeader(@RequestHeader(value = "X-Request-red", required = false) String header) {
        return header;
    }

}
