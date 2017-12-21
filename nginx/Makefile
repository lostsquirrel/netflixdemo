SHELL=/bin/bash

VERSION=v1.$(shell git rev-parse --short HEAD)
IMAGETAG=$(VERSION)
# if use another registry add  MY_REGISTRY=registry.lisong.pub:5000 to ~/.bashrc
REGISTRY=${MY_REGISTRY:registry.cn-hangzhou.aliyuncs.com}
IMAGE=$(REGISTRY)/lisong/cloud-web
VERSIONED_IMAGE=$(IMAGE):$(IMAGETAG)

export VERSIONED_IMAGE

# 登陆registry
login:
	docker login --username=songli1988@126.com registry.cn-hangzhou.aliyuncs.com --password=luyou123

# Push to registry. 推送到远程仓库
push:
	docker push $(VERSIONED_IMAGE)

pull:
	docker pull $(VERSIONED_IMAGE)

code:
	git reset --hard HEAD && git pull


build:
	docker build --no-cache -t $(VERSIONED_IMAGE) \
	.

test:
	docker run --rm  $(VERSIONED_IMAGE)

start: build remove
	docker stack deploy --with-registry-auth -c nginx.yml nginx
	
remove:
	docker stack rm nginx
# 删除构建失败的镜像
:
#	docker rmi -f $(docker images | grep "<none>" | awk '{print $3}')
#	docker rmi -f $(docker images -f "dangling=true" -q)
#	/sbin/ip route|awk '/default/ { print $3 }'
.PHONY: push pull package build test start