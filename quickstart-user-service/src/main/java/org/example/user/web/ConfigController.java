package org.example.user.web;

import org.example.user.pojo.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
@RefreshScope // 配置热更新
public class ConfigController {

    @Value("${pattern.dateformat}")
    private String dateformat;
    @Autowired
    private Pattern pattern;

    /**
     * 获取Nacos服务中管理的配置
     *
     * 方式一：@RefreshScope
     */
    @GetMapping
    public String remoteConfig() {
        return dateformat;
    }

    /**
     * 配置热更新
     *
     * 方式二：@ConfigurationProperties
     */
    @GetMapping("/properties")
    public Pattern configProperties() {
        return pattern;
    }

}
