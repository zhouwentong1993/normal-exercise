package com.wentong.performance;

import com.google.common.base.Stopwatch;
import redis.clients.jedis.Jedis;

public class PureJedisTest {

    // 10w 请求，get 6.748 s
    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        try (Jedis jedis = new Jedis()) {
            for (int i = 0; i < 100000; i++) {
                System.out.println(jedis.get("a"));
            }
        }
        stopwatch.stop();
        System.out.println(stopwatch);
    }

}
