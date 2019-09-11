package com.wentong.springbootredisson.controller;

import com.wentong.springbootredisson.aspect.DistributeLock;
import com.wentong.springbootredisson.vo.User;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("lock/{name}")
    @DistributeLock(lockUniqueKey = "#user.userId",lockPrefix = "testLock")
    public String getLock(@PathVariable String name, @RequestBody User user) throws Exception {
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
