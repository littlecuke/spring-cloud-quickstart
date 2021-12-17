package org.example.mq.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TopicQueueListener {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("topic.queue1"),
            exchange = @Exchange(
                    value = "example.topic",
                    type = ExchangeTypes.TOPIC
            ),
            key = "example.#"
    ))
    public void listen(String message) {
        log.info("Topic Queue [1]: {}", message);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("topic.queue2"),
            exchange = @Exchange(
                    value = "example.topic",
                    type = ExchangeTypes.TOPIC
            ),
            key = "#.news"
    ))
    public void listen2(String message) {
        log.info("Topic Queue [2]: {}", message);
    }

}
