package org.example.mq;

import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class ConsumerTest {

    @Test
    public void receive() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.0.108");
        factory.setUsername("admin");
        factory.setPassword("123456");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        // 1.建立连接
        Connection conn = factory.newConnection();
        // 2.创建通道
        Channel channel = conn.createChannel();
        // 3.创建队列
        String queueName = "simple.queue";
        channel.queueDeclare(queueName, false, false, false, null);
        // 4.订阅消息
        DeliverCallback deliverCallback = (consumerTag, deliver) -> {
            String message = new String(deliver.getBody(), StandardCharsets.UTF_8);
            System.out.println("[x] Received '" + message + "'");
        };
        channel.basicConsume(queueName,  true, deliverCallback,
                consumerTag -> {});
    }

}
