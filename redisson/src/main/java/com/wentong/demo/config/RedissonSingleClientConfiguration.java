package com.wentong.demo.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonSingleClientConfiguration {
    @Bean
    RedissonClient redissonSingle() {
        Config config = new Config();
        String node = "127.0.0.1";
        node = node.startsWith("redis://") ? node : "redis://" + node;
        SingleServerConfig serverConfig = config.useSingleServer()
                .setAddress(node)
                .setTimeout(3000)
                .setConnectionPoolSize(100);
        return Redisson.create(config);
    }
}
