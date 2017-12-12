SHELL=/bin/bash

VERSION=$(shell cat version.txt)
IMAGETAG=$(VERSION)
REGISTRY=registry.cn-hangzhou.aliyuncs.com
IMAGE=$(REGISTRY)/lisong/eureka-server
VERSIONED_IMAGE=$(IMAGE):$(IMAGETAG)

# 登陆registry
login:
	docker login --username=songli1988@126.com registry.cn-hangzhou.aliyuncs.com --password=luyou123

# Push to registry. 推送到远程仓库
push:
	docker push $(VERSIONED_IMAGE)

pull:
	docker pull $(VERSIONED_IMAGE)

build:
	docker build --no-cache -t $(VERSIONED_IMAGE) -f $(UBUNTU_VERSION)-$(CUSTOM_VERSION)/Dockerfile .

test:
	docker run --rm  $(VERSIONED_IMAGE)

start:
    docker-compose up -d
# 删除构建失败的镜像
clean:
	docker rmi -f $(docker images | grep "<none>" | awk '{print $3}')
	docker rmi -f $(docker images -f "dangling=true" -q)