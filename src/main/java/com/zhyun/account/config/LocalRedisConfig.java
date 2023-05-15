package com.zhyun.account.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import redis.embedded.RedisServer;

@Configuration
public class LocalRedisConfig {

    @Value("${spring.data.redis.port}") // lombok이 아니고 bean.factory
    private int redisPort;


    private RedisServer redisServer;

    @PostConstruct
    public void startRedisssss() {
        redisServer = new RedisServer(redisPort);
        redisServer.start();
    }

    @PreDestroy
    public void endRedissssss() {
        if (redisServer != null)
            redisServer.stop();
    }
}
