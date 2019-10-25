package com.wentong.jedis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class TestLua {
    public static void main(String[] args) {
        HostAndPort hostAndPort = new HostAndPort("127.0.0.1", 7000);
        JedisCluster jedisCluster = new JedisCluster(hostAndPort);
        jedisCluster.set("testCluster", "yes");
//        jedisCluster.eval()
        System.out.println(jedisCluster.get("testCluster"));
    }
}
