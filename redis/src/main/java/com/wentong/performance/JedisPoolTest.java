package com.wentong.performance;

import com.google.common.base.Stopwatch;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

// 采用连接池的模式，10w get 请求总共耗时 4.688s
public class JedisPoolTest {

    public static void main(String[] args) {

        Stopwatch stopwatch = Stopwatch.createStarted();
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(10);
        config.setMaxWaitMillis(1000);
        // redisHost为实例的IP， redisPort 为实例端口，redisPassword 为实例的密码，timeout 既是连接超时又是读写超时
        JedisPool jedisPool = new JedisPool(config, "127.0.0.1");
        Jedis jedis = null;
        for (int i = 0; i < 1000000; i++) {
            try {
                jedis = jedisPool.getResource();
                //具体的命令
                jedis.get("a");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //在 JedisPool 模式下，Jedis 会被归还给资源池
                if (jedis != null)
                    jedis.close();
            }
        }
        stopwatch.stop();
        System.out.println(stopwatch);

    }

}
