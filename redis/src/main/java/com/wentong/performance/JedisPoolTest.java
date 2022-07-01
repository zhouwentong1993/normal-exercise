package com.wentong.performance;

import com.google.common.base.Stopwatch;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

// 采用连接池的模式，10w get 请求总共耗时 4.688s
public class JedisPoolTest {

    public static void main(String[] args) throws Exception {

        Stopwatch stopwatch = Stopwatch.createStarted();
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(10);
        config.setMaxWaitMillis(100000);
        // redisHost为实例的IP， redisPort 为实例端口，redisPassword 为实例的密码，timeout 既是连接超时又是读写超时
        JedisPool jedisPool = new JedisPool(config, "127.0.0.1");
        Set<String> connections = new HashSet<>();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                try {
                    countDownLatch.await();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                Jedis jedis = null;
                try {
                    jedis = jedisPool.getResource();
                    System.out.println(jedis);
                    //具体的命令
                    jedis.get("a");
                    connections.add(jedis.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    //在 JedisPool 模式下，Jedis 会被归还给资源池
                    if (jedis != null) {
                        jedis.close();
                    }
                }
            }).start();
        }
        countDownLatch.countDown();

        TimeUnit.SECONDS.sleep(3);
        System.out.println(connections.size());
        stopwatch.stop();
        System.out.println(stopwatch);

    }

}
