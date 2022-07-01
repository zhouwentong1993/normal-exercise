package com.wentong;

import com.wentong.config.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("redis")
public class Application {

    @Autowired
    private RedisClient redisClient;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @GetMapping("get")
    public String get(String key) {
        return redisClient.get(key);
    }
}
