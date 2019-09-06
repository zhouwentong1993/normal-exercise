package com.wentong.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * 订阅 Redis key 超时事件
 */
public class NotifyKeySpaceEvent {

    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        String s = jedis.clientGetname();
        System.out.println(s);

        jedis.setex("aaaaa", 15, "aaaaa");

        jedis.psubscribe(new JedisPubSub() {
            @Override
            public void onPMessage(String pattern, String channel, String message) {
                System.out.println("time now = " + System.currentTimeMillis());
                System.out.println(pattern + "    " + channel +"    " + message);
            }
        }, "__keyevent@0__:expired");
        jedis.close();
    }

}
