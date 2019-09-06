package com.wentong.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;

public class AccountBalanceTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        String userId = "userId";
        String key = keyFor(userId);
        jedis.setnx(key, String.valueOf(5));
        System.out.println(get(jedis, userId));
        jedis.close();
    }

    private static String get(Jedis jedis,String s) {
        String key = keyFor(s);
        while (true) {
            jedis.watch(key);
            int count = Integer.parseInt(jedis.get(key));
            count = count * 2;
            Transaction multi = jedis.multi();
            multi.set(key, String.valueOf(count));
            List<Object> exec = multi.exec();
            if (!exec.isEmpty()) {
                break;
            }
        }
        return jedis.get(key);
    }

    private static String keyFor(String source) {
        return String.format("account_%s", source);
    }
}
