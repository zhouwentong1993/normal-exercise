package com.finup.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

public class SizeLimit {
    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        for (int i = 0; i < 512; i++) {
            jedis.hset("hh", String.valueOf(i), String.valueOf(i));
        }
        jedis.close();
    }

    @Test
    public void testKeyIncr() {
        Jedis jedis = new Jedis();
        for (int i = 0; i < 64; i++) {
            jedis.hset("tt", String.valueOf(i), dupString(String.valueOf(i), i));
        }
        jedis.close();
    }

    private String dupString(String source, int times) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < times; i++) {
            stringBuilder.append(source);
        }
        return stringBuilder.toString();
    }

}
