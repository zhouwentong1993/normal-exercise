package com.wentong.springbootredisson;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("test")
public class TestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);
    @Autowired
    private RedissonClient redissonClient;

    @GetMapping("get")
    public String get() {
        return "hello";
    }

    @GetMapping("lock/{name}")
    public String getLock(@PathVariable String name) throws Exception {
        RLock lock = redissonClient.getLock(name);
        boolean b = lock.tryLock(5,TimeUnit.SECONDS);
        if (b) {
            LOGGER.info("get lock by:{}", Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(5);
                lock.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "getLock";
        } else {
            return "unGetLock";
        }
    }

}
