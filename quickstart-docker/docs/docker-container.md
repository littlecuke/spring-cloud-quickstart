## Docker Container

### 1.创建并运行一个容器

```bash
docker run --name my-nginx -p 80:80 -d nginx
```

- docker run：创建并启动一个容器
- --name my-nginx：指定容器的名称
- -p 80:80：指定宿主机与容器端口的映射，左边是宿主机的端口，右边是容器端口
- -d：后台运行
- nginx：镜像名称

### 2.查看容器

![docker_ps](./assets/docker_ps.png)

- 查看容器

```bash
docker ps
```
- 查看全部容器

```bash
docker ps -a
```

### 3.查看指定容器的日志

```bash
docker logs CONTAINER ID
```

### 4.进入容器内部

```bash
docker exec -it my-nginx bash
```

- docker exec：进入容器内部执行一个命令
- -it：给当前进入的容器创建一个终端，允许我们与容器交互
- my-nginx：容器的名称
- bash：进入容器后执行的命令，bash是一个linux终端交互命令

### 5.停止运行

#### 5.1 获取容器ID

```bash
docker ps
```

![docker_ps](./assets/docker_ps.png)

#### 5.2 停止运行

```bash
docker stop f4b4b933f7c8
```

#### 5.3 启动

```bash
docker start f4b4b933f7c8
```

也可以使用：

```bash
docker stop my-nginx
```

```bash
docker start my-nginx
```
