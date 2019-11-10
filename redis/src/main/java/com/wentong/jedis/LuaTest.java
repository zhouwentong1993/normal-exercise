package com.wentong.jedis;

import redis.clients.jedis.Jedis;

public class LuaTest {

    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        Object eval = jedis.eval("return redis.call('info')");
        System.out.println(eval);
    }

}
