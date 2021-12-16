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
```yml
spring:
  rabbitmq:
    host: 192.168.0.108
    port: 5672
    virtual-host: /
    username: admin
    password: 123456
```
