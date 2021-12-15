package org.example.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class PublisherTest {

    @Test
    public void publish() throws IOException, TimeoutException {
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
        // 4.发送消息
        String message = "Hello RabbitMQ!";
        channel.basicPublish("", queueName, null, message.getBytes(StandardCharsets.UTF_8));
        System.out.println("消息：" + message + " 发送成功！");
        // 5.释放资源
        channel.close();
        conn.close();
    }

}
