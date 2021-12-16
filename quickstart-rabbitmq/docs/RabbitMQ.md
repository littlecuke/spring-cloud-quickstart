## RabbitMQ

本教程中将采用Docker搭建RabbitMQ的测试环境

### 1.拉取RabbitMQ的镜像

```bash
docker pull rabbitmq:3-management
```

### 2.运行

```bash
docker run \
  --name mq \
  --hostname mq-host \
  -p 15672:15672 \
  -p 5672:5672 \
  -e RABBIT_DEFAULT_USER=admin \
  -e RABBIT_DEFAULT_PASS=123456 \
  -d \
  rabbitmq:3-management
```

### 3.基本概念

- channel：操作MQ的工具
- exchange：路由消息到队列中
- queue：缓存消息
- virtual host：虚拟主机，是对queue、exchange等资源的逻辑分组

### 4.案例

#### HelloWorld

官方的HelloWorld是基于最基础的消息队列模型来实现的，只包括三个角色：

- publisher：消息发布者，将消息发送到队列中
- queue：消息队列，负责接收并缓存消息
- consumer：消息消费者，处理消息队列中缓存的消息

### 5.什么是AMQP?

应用之间消息通信的一种协议，与语言和平台无关

### 6.SpringAMQP如何发送消息?

- 引入 `spring-boot-starter-amqp` 依赖
- 配置RabbitMQ
```yaml
spring:
  rabbitmq:
    host: 192.168.0.108
    port: 5672
    virtual-host: /
    username: admin
    password: 123456
```
- 利用RabbitTemplate的convertAndSend方法
```java
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

}
```

### 7.SpringAMQP如何接收消息?

- 引入 `spring-boot-starter-amqp` 依赖
- 配置RabbitMQ

```yaml
spring:
  rabbitmq:
    host: 192.168.0.108
    port: 5672
    virtual-host: /
    username: admin
    password: 123456
```

- 定义一个有@Component注解的类
- 类中声明方法，添加@RabbitListener注解，方法的形式参数就是接收到的消息

```java
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SpringAMQPListener {

    public static final String QUEUE_NAME = "simple.queue";

    @RabbitListener(queues = { QUEUE_NAME })
    public void listenSimpleQueue(String message) {
        log.info("[x] Spring AMQP received: {}", message);
    }
}
```

### 8.Work模型的使用

- 多个消费者绑定到一个队列，同一条消息只会被一个消费者处理
- 通过设置prefetch来控制消费者预取的消息数量

