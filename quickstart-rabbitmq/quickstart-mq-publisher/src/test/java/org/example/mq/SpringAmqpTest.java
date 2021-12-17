package org.example.mq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringAmqpTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSimpleQueue() {
        String queueName = "simple.queue";
        String message = "Hello RabbitMQ!";
        rabbitTemplate.convertAndSend(queueName, message);
    }

    /**
     * 模拟WorkQueue，实现一个队列绑定多个消费者
     * 工作队列，可以提高消息的处理速度，避免队列消息堆积
     */
    @Test
    public void testWorkQueue() throws InterruptedException {
        String queueName = "simple.queue";
        String message = "Hello Message_";
        for (int i = 1; i <= 50; i++) {
            rabbitTemplate.convertAndSend(queueName, message + i);
            Thread.sleep(20);
        }
    }

    @Test
    public void testSendFanoutMessage() {
        String exchangeName = "example.fanout";
        String message = "Hello World!";
        rabbitTemplate.convertAndSend(exchangeName, "", message);
    }

    @Test
    public void testDirectExchange() {
        String exchangeName = "example.direct";
        String message = "Hello Example!";
        rabbitTemplate.convertAndSend(exchangeName, "example", message);
    }

    @Test
    public void testTopicExchange() {
        String exchangeName = "example.topic";
        String message = "From Topic Exchange!";
        rabbitTemplate.convertAndSend(exchangeName, "example.news", message);
    }

    /**
     * 测试RabbitMQ发送消息时自动序列化
     */
    @Test
    public void testSendObjectQueue() {
        String queue = "object.queue";

        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("message", "请求成功");

        rabbitTemplate.convertAndSend(queue, map);
    }

}
