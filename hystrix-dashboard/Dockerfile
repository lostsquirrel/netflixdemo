FROM registry.cn-hangzhou.aliyuncs.com/luyou/tomcat:8.5.4
ADD Hystrix/hystrix-dashboard/build/libs/hystrix-dashboard-*.war $CATALINA_HOME/webapps

EXPOSE 8080
CMD ["catalina.sh", "run"]
