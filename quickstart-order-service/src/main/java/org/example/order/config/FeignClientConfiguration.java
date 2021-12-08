package org.example.order.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * Feign 客户端配置类
 * <p>
 * 使用方式:
 * - 全局配置：@EnableFeignClients(defaultConfiguration = FeignClientConfiguration.class)
 * - 局部配置：@FeignClient(value = "userservice", configuration = FeignClientConfiguration.class)
 *
 * 底层客户端实现:
 *  - URLConnection: 默认实现不支持连接池
 *  - Apache HttpClient: 支持连接池
 *  - OkHttp: 支持连接池
 *
 * Feign性能优化:
 *  - 使用连接池代替默认的URLConnection
 *  - 日志级别，最好使用 BASIC 或 NONE
 */
public class FeignClientConfiguration {
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.BASIC;
    }
}
