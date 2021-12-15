package org.example.mq;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.StandardCharsets;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsumerApplicationTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSimpleQueue() {
        String queueName = "simple.queue";
        Message receivedMessage = rabbitTemplate.receive(queueName);
        Assert.assertNotNull(receivedMessage);
        byte[] body = receivedMessage.getBody();
        log.info(new String(body, StandardCharsets.UTF_8));
    }

}
