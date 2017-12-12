FROM registry.cn-hangzhou.aliyuncs.com/lisong/java:8_server-jre
VOLUME /tmp
ARG JAR_FILE
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]