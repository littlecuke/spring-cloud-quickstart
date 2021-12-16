package org.example.mq.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WorkQueueListener {

    public static final String QUEUE_NAME = "simple.queue";

    @RabbitListener(queues = { QUEUE_NAME })
    public void listen(String message) throws InterruptedException {
        log.info("Consumer [1]: {}", message);
        Thread.sleep(20);
    }

    @RabbitListener(queues = { QUEUE_NAME })
    public void listen2(String message) throws InterruptedException {
        log.info("Consumer [2]: {}", message);
        Thread.sleep(20);
    }

}
