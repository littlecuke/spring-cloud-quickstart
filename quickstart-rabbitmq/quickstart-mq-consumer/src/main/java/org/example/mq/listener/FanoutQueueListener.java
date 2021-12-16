package org.example.mq.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FanoutQueueListener {

    @RabbitListener(queues = { "fanout.queue1" })
    public void listenFanoutQueue(String message) {
        log.info("[1] Received Fanout Queue: {}", message);
    }

    @RabbitListener(queues = { "fanout.queue2" })
    public void listenFanoutQueue2(String message) {
        log.info("[2] Received Fanout Queue: {}", message);
    }

}
