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

### 9.发布订阅模型

发布订阅模式与之前案例的区别就是允许将同一消息发送给多个消费者。实现方式是加入了exchange(交换机)。

**常见的exchange类型：**

- Fanout：广播
- Direct：路由
- Topic：话题

**注意：exchange负责的是消息的路由，而不是存储，路由失败则消息丢失**

#### FanoutExchange

- 创建一个配置类并声明FanoutExchange、Queue和绑定关系对象的Binding

```java
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutConfig {

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("example.fanout");
    }

    @Bean
    public Queue fanoutQueue1() {
        return new Queue("fanout.queue1");
    }

    @Bean
    public Binding bindingQueue1(Queue fanoutQueue1, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue1).to(fanoutExchange);
    }
    
    // ... queue2

}
```

- 在publisher服务中添加测试方法

```java
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringAmqpTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void sendFanout() {
        // 定义交换机的名称
        String exchangeName = "example.fanout";    
        // 发送的消息
        String message = "Hello World!";
        rabbitTemplate.convertAndSend(exchangeName, "", message);
    }
    
}
```

##### DirectExchange

Direct Exchange 会将接收到的消息根据规则路由到指定的队列，因此称为路由模式(routes)

- 每一个Queue都会与Exchange设置一个BindingKey
- 发布者在发布消息时要指定绑定的BindingKey
- 交换机将消息路由到BindingKey与消息RoutingKey一致的队列

**代码实现：**

- 在Consumer服务中编写两个消费者方法，分别监听direct.queue1和direct.queue2
- 并利用@RabbitListener声明Exchange、Queue、RoutingKey

```java
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DirectQueueListener {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("direct.queue1"),
            exchange = @Exchange("example.direct"),
            key = {"example"}
    ))
    public void listen(String message) {
        log.info("Direct Queue1: {}", message);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("direct.queue2"),
            exchange = @Exchange("example.direct"),
            key = {"example2"}
    ))
    public void listen2(String message) {
        log.info("Direct Queue2: {}", message);
    }

}
```

- 添加测试方法发送消息

```java
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringAmqpTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void directQueue() {
        // 定义交换机的名称
        String exchange = "example.direct";
        // 定义发送的消息
        String message = "Hello Direct!";
        rabbitTemplate.convertAndSend(exchange, "example", message);
    }

}
```

### 10.Direct交换机与Fanout交换机的区别

- Fanout交换机将消息路由给每个与之绑定的队列
- Direct交换机是根据RoutingKey判断路由给哪个队列
- 如果队列具有相同的RoutingKey，则与Fanout功能相似

