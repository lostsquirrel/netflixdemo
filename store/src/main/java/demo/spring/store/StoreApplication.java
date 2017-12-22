package demo.spring.store;

import demo.spring.store.po.Store;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(StoreApplication.class).web(true).run(args);
    }

    @Configuration
    @EnableDiscoveryClient
    static class CloudConfig {}

    @Value("${spring.redis.host}")
    String host;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {

        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName(host);
        return jedisConnectionFactory;
    }

    @Bean
    public RedisTemplate storeRedisTemplate() {
        RedisTemplate<String, Store> stringStoreRedisTemplate = new RedisTemplate<>();
        stringStoreRedisTemplate.setKeySerializer(new StringRedisSerializer());
//        stringStoreRedisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<Store>(Store.class));
        stringStoreRedisTemplate.setConnectionFactory(redisConnectionFactory());
        return stringStoreRedisTemplate;
    }


}
