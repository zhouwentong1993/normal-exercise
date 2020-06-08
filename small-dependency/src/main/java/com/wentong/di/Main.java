package com.wentong.di;

import com.wentong.di.object.RateLimiter;
import com.wentong.di.object.RedisCounter;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        RateLimiter rateLimiter = (RateLimiter) applicationContext.getBean("rateLimiter");

        RedisCounter redisCounter = (RedisCounter) applicationContext.getBean("redisCounter");
        RedisCounter redisCounter1 = (RedisCounter) applicationContext.getBean("redisCounter");
        System.out.println(rateLimiter);
        System.out.println(redisCounter.hashCode());
        System.out.println(redisCounter1.hashCode());
    }
}
