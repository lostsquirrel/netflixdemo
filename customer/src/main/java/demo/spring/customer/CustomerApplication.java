package demo.spring.customer;

import feign.Feign;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.hypermedia.DynamicServiceInstanceProvider;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
public class CustomerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }

    @EnableDiscoveryClient
    static class CloudConfiguration {

        @Bean
        public DynamicServiceInstanceProvider dynamicServiceProvider(DiscoveryClient client) {
            return new DynamicServiceInstanceProvider(client, "stores");
        }
    }


}
