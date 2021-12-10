## CentOS Docker 安装

### 一、官方脚本安装

```bash
curl -fsSL https://get.docker.com | bash -s docker --mirror Aliyun
```

国内 daocloud

```bash
curl -sSL https://get.daocloud.io/docker | sh
```

### 二、手动安装

#### 1.卸载旧版本

```bash
sudo yum remove docker \
				docker-client \
				docker-client-latest \
				docker-common \
				docker-latest \
				docker-latest-logrotate \
				docker-logrotate \
				docker-engine
```

#### 2.安装 Docker Engine-Community

如果是第一次安装，需要设置 docker 仓库

```bash
sudo yum install -y yum-utils \
					device-mapper-persistent-data \
					lvm2
```

设置源，这里采用阿里云的源

```bash
sudo yum-config-manager \
	--add-repo \
	http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
```

安装 docker engine-community

```bash
sudo yum install docker-ce docker-ce-cli containerd.io
```

### 三、启动

```bash
sudo systemctl start docker
```

### 四、验证

```bash
sudo docker run hello-world
```

### 五、卸载

#### 1.删除安装包

```bash
yum remove docker-ce
```

#### 2.删除镜像、容器、配置文件等

```bash
rm -rf /var/lib/docker
```
