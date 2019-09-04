package com.wentong.demo.controller;

import org.redisson.api.RKeys;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("lock")
public class LockController {

    @Autowired
    private RedissonClient redissonClient;

    @GetMapping("get")
    public String get() {
        RKeys keys = redissonClient.getKeys();
        System.out.println(keys.count());
        return "";
    }

}
