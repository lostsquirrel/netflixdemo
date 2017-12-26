package demo.spring.eureka.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import java.util.Arrays;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
    private  static final Logger logger = LoggerFactory.getLogger(EurekaServerApplication.class);

    public static void main(String[] args) {
        logger.debug(Arrays.toString(args));
        new SpringApplicationBuilder(EurekaServerApplication.class).web(true).run(args);
    }
}
