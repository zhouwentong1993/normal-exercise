package com.wentong.jedis;

import redis.clients.jedis.Jedis;

public class ClientListTest {

    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        String s = jedis.clientSetname("test".getBytes());
        System.out.println(s);
        System.out.println(jedis.clientList());

    }

}
