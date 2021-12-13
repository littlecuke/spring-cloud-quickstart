## Docker Images

### 1.镜像拉取

```bash
docker pull nginx
```

### 2.指定版本获取镜像

```bash
docker pull ubuntu:13.10
```

### 3.查看镜像列表

```bash
docker images
```

### 4.查找镜像

```bash
docker search httpd
```

### 5.删除镜像

```bash
docker rmi hello-world
```

### 6.将镜像打包到本地

```bash
docker save -o redis.tar redis:latest
```

### 7.从本地加载镜像

```bash
docker load -i redis.tar
```

### 8.常见问题

#### 1.虚拟机桥接模式下，启动容器后没有网络

```bash
WARNING: IPv4 forwarding is disabled. Networking will not work.
```

**解决: 配置转发**

```bash
vim /etc/sysctl.conf

# 配置转发
net.ipv4.ip_forward=1

# 重启网络
systemctl restart network

# 如果返回 'net.ipv4.ip_forward=1' 表示成功
sysctl net.ipv4.ip_forward
```