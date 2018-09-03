package com.finup.demo;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;

public class Demo1 {
    public static void main(String[] args) {
        RedissonClient redisson = Redisson.create();
        System.out.println(redisson);

//        Config config = new Config();
//        config.useSingleServer().setAddress("myredisserver:6379");
//        RedissonClient redisson = Redisson.create(config);
    }
}
