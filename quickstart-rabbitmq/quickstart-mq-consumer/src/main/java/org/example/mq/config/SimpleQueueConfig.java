package org.example.mq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SimpleQueueConfig {

    /**
     * 注入SimpleQueueListener中需要的队列，避免启动Consumer服务时的报错
     */
    @Bean
    public Queue simpleQueue() {
        return new Queue("simple.queue");
    }

}
