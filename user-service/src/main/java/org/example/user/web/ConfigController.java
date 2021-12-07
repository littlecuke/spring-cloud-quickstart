package org.example.user.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
public class ConfigController {

    @Value("${pattern.dateformat}")
    private String dateformat;

    /**
     * 获取Nacos服务中管理的配置
     */
    @GetMapping
    public String remoteConfig() {
        return dateformat;
    }

}
