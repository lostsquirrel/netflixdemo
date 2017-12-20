SHELL=/bin/bash

VERSION=1.0-SNAPSHOT
IMAGETAG=$(VERSION)
REGISTRY=registry.cn-hangzhou.aliyuncs.com
IMAGE=$(REGISTRY)/lisong/eureka-server
VERSIONED_IMAGE=$(IMAGE):$(IMAGETAG)
JAR_FILE=target/eureka-server-$(VERSION).jar
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

start: remove
	docker stack deploy  -c docker-compose.yml eureka
	
remove:
	docker stack rm eureka
# 删除构建失败的镜像
:
#	docker rmi -f $(docker images | grep "<none>" | awk '{print $3}')
#	docker rmi -f $(docker images -f "dangling=true" -q)
#	/sbin/ip route|awk '/default/ { print $3 }'
.PHONY: push pull package build test start