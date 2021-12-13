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
