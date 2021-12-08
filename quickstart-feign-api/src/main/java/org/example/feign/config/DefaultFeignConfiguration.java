package org.example.feign.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * 默认feign配置类
 */
public class DefaultFeignConfiguration {
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.BASIC;
    }
}
