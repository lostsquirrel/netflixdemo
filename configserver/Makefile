SHELL=/bin/bash

VERSION=1.0-SNAPSHOT
IMAGETAG=$(VERSION)
# if use another registry add  MY_REGISTRY=registry.lisong.pub:5000 to ~/.bashrc
ifndef REGISTRY
    REGISTRY=registry.cn-hangzhou.aliyuncs.com
endif
IMAGE=$(REGISTRY)/lisong/config-server
VERSIONED_IMAGE=$(IMAGE):$(IMAGETAG).$(shell git rev-parse --short HEAD)
JAR_FILE=target/config-server-$(VERSION).jar
export VERSIONED_IMAGE
export JAR_FILE
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

package:
	mvn clean package

build: package
	docker build --no-cache -t $(VERSIONED_IMAGE) \
	--build-arg JAR_FILE=$(JAR_FILE) \
	.

test:
	docker run --rm  $(VERSIONED_IMAGE)

start:
	docker stack deploy --with-registry-auth -c docker-compose.yml config
# 删除构建失败的镜像
# clean:
#	docker rmi -f $(docker images | grep "<none>" | awk '{print $3}')
#	docker rmi -f $(docker images -f "dangling=true" -q)

.PHONY: push pull package build test start