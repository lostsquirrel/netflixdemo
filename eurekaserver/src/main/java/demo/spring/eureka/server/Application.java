package demo.spring.eureka.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
@EnableEurekaServer
public class Application {
    private  static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        logger.debug(Arrays.toString(args));
        new SpringApplicationBuilder(Application.class).web(true).run(args);
    }

    @Value("${server.port:8761}")
    private int port;

    @Value("${eureka.instance.hostname:localhost}")
    private String hostname;

    @Value("${eureka.instance.ip-address}")
    private String ipAddress;
    @Bean
    @Autowired
    public EurekaInstanceConfigBean eurekaInstanceConfigBean(InetUtils inetUtils) {
        EurekaInstanceConfigBean config = new EurekaInstanceConfigBean(inetUtils);
//        AmazonInfo info = AmazonInfo.Builder.newBuilder().autoBuild("eureka");
        config.setHostname(hostname);
        config.setIpAddress(ipAddress);
        config.setNonSecurePort(port);
//        config.setDataCenterInfo(info);
        return config;
    }
}
