package com.wentong.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TestOOM {

    public static void main(String[] args) throws Exception {
        Jedis jedis = new Jedis();
        for (int i = 0; i < 1000000; i++) {
            jedis.zadd("testZset", System.currentTimeMillis() + 10 * 60 * 60 * 1000D, "abc" + i);
        }
        TimeUnit.SECONDS.sleep(10);
        while (true) {
            Set<Tuple> testZset = jedis.zrangeWithScores("testZset", 0, -1);
            System.out.println(testZset.size());
        }


    }


}
