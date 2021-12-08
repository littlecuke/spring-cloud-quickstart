package org.example.order.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Feign 客户端配置类
 * <p>
 * 使用方式:
 * - 全局配置：@EnableFeignClients(defaultConfiguration = FeignClientConfiguration.class)
 * - 局部配置：@FeignClient(value = "userservice", configuration = FeignClientConfiguration.class)
 */
@Configuration
public class FeignClientConfiguration {
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.BASIC;
    }
}
