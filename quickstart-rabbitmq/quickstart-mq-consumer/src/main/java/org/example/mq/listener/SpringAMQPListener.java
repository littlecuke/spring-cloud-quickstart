package org.example.mq.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SpringAMQPListener {

    public static final String QUEUE_NAME = "simple.queue";

    @RabbitListener(queues = { QUEUE_NAME })
    public void listenSimpleQueue(String message) {
        log.info("Spring consumer received: {}", message);
    }

}
