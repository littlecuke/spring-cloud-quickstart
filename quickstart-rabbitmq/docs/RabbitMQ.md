## RabbitMQ 环境搭建

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