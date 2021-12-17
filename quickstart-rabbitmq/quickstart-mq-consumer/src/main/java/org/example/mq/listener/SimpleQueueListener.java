package org.example.mq.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 注意：这里的simple.queue如果是第一次启动会报错，找不到该队列，
 * 需要Publisher服务中先将消息发送到simple.queue的队列中
 */
@Slf4j
@Component
public class SimpleQueueListener {

    public static final String QUEUE_NAME = "simple.queue";

    @RabbitListener(queues = {QUEUE_NAME})
    public void listenSimpleQueue(String message) {
        log.info("Spring consumer received: {}", message);
    }

}
