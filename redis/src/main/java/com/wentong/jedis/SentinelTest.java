package com.wentong.jedis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;

public class SentinelTest {

    public static void main(String[] args) throws Exception {
        Jedis jedis = new Jedis();
        HashSet<String> sentinels = new HashSet<>();
        sentinels.add("127.0.0.1:6481");
        JedisSentinelPool pool = new JedisSentinelPool("mymaster", sentinels);
        while (true) {
            HostAndPort currentHostMaster = pool.getCurrentHostMaster();
            System.out.println(currentHostMaster);
            TimeUnit.SECONDS.sleep(1);
        }
    }

}
