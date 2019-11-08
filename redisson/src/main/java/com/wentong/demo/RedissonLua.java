package com.wentong.demo;

import com.google.common.collect.Lists;
import org.redisson.Redisson;
import org.redisson.api.RScript;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;

import java.util.concurrent.Executors;

public class RedissonLua {

    public static void main(String[] args) {
        Config config = new Config();
        config.setTransportMode(TransportMode.NIO);
        config.useSingleServer().setAddress("http://127.0.0.1:6379");
        config.setExecutor(Executors.newFixedThreadPool(2000));
        // 指定分布式锁中未添加超时时间的情i况，默认值
        config.setLockWatchdogTimeout(30000);

        RedissonClient redisson = Redisson.create(config);
        String script = "return redis.call('info')";
        Object eval1 = redisson.getScript().eval(
                RScript.Mode.READ_ONLY, //执行模式
                "return redis.call('get', KEYS[1])",    // 要执行的lua脚本
                RScript.ReturnType.INTEGER, // 返回值类型
                Lists.newArrayList("tac"),  // 传入KEYS
                true, 1L, "hello"   //传入ARGV
        );
        System.out.println(eval1);
        Object eval = redisson.getScript().eval(RScript.Mode.READ_WRITE, script, RScript.ReturnType.STATUS);
        System.out.println(eval);

    }

}
