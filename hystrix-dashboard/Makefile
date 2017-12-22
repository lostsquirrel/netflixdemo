SHELL=/bin/bash
-include *.mk
VERSION=1.0-SNAPSHOT
IMAGETAG=$(VERSION)
# if use another registry add  MY_REGISTRY=registry.lisong.pub:5000 to ~/.bashrc
ifndef REGISTRY
    REGISTRY=registry.cn-hangzhou.aliyuncs.com
endif
IMAGE=$(REGISTRY)/lisong/hytrinx-dashboard
VERSIONED_IMAGE=$(IMAGE):$(IMAGETAG).$(shell git rev-parse --short HEAD)

export VERSIONED_IMAGE

# 登陆registry
login:
	docker login --username=songli1988@126.com registry.cn-hangzhou.aliyuncs.com --password=luyou123
code:
	git reset --hard HEAD && git pull
package:
	mvn clean package
# Push to registry. 推送到远程仓库
push:
	docker push $(VERSIONED_IMAGE)

pull:
	docker pull $(VERSIONED_IMAGE)

build:
	docker build --no-cache -t $(VERSIONED_IMAGE) \
	.

test:
	docker run --rm  $(VERSIONED_IMAGE)

start: build remove
	docker stack deploy --with-registry-auth -c docker-compose.yml hytrinx-dashboard

remove:
	docker stack rm hytrinx-dashboard
# 删除构建失败的镜像
:
#	docker rmi -f $(docker images | grep "<none>" | awk '{print $3}')
#	docker rmi -f $(docker images -f "dangling=true" -q)
#	/sbin/ip route|awk '/default/ { print $3 }'
.PHONY: push pull package build test start
