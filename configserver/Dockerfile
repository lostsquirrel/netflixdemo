FROM registry.cn-hangzhou.aliyuncs.com/lisong/java:8_server-jre
ARG JAR_FILE
ADD ${JAR_FILE} app.jar
VOLUME /tmp
VOLUME /target
RUN bash -c 'touch /app.jar'
EXPOSE 8888
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar", "/app.jar"]