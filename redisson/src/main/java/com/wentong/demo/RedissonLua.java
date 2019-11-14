package com.wentong.demo;

import org.redisson.Redisson;
import org.redisson.api.RScript;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;

import java.util.concurrent.Executors;


// 要实现监控，Redisson 无法执行 lua 的 info。
// 可以通过 jedisCluster 来获取集群底下所有的节点，然后分别对各个节点运行 info 命令。
// 顺便说一句，jedisCluster 不支持 info 命令，直接抛出异常。作者解释到这种命令在集群环境下没有意义，这也是写代码一种非常好的思路
// 就是不做没有意义的事情。
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
//        Object eval1 = redisson.getScript().eval(
//                RScript.Mode.READ_ONLY, //执行模式
//                "return redis.call('get', KEYS[1])",    // 要执行的lua脚本
//                RScript.ReturnType.INTEGER, // 返回值类型
//                Lists.newArrayList("tac"),  // 传入KEYS
//                true, 1L, "hello"   //传入ARGV
//        );
//        System.out.println(eval1);
//        BOOLEAN(RedisCommands.EVAL_BOOLEAN),
//                INTEGER(RedisCommands.EVAL_LONG),
//                MULTI(RedisCommands.EVAL_LIST),
//                STATUS(RedisCommands.EVAL_STRING),
//                VALUE(RedisCommands.EVAL_OBJECT),
//                MAPVALUE(RedisCommands.EVAL_MAP_VALUE),
//                MAPVALUELIST(RedisCommands.EVAL_MAP_VALUE_LIST);

        Object eval = redisson.getScript().eval(RScript.Mode.READ_ONLY, script, RScript.ReturnType.STATUS);
        System.out.println(eval);

    }

}
