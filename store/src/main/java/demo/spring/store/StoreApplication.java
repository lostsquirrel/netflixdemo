package demo.spring.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(StoreApplication.class).web(true).run(args);
    }

    @Configuration
    @EnableDiscoveryClient
    static class CloudConfig {}
}
