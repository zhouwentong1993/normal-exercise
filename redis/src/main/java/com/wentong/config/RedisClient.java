package com.wentong.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangnan on 2017/11/6.
 */
@Service
@Slf4j
public class RedisClient {

    private final JedisPool jedisPool;
    private final Integer time = 3;//缓存3秒

    /**
     * EX：存在则插入
     * NX：不存在则插入
     */
    private static final String SET_IF_EXIST = "EX";
    private static final String SET_IF_NOT_EXIST = "NX";

    /**
     * EX：秒
     * PX：毫秒
     */
    private static final String SET_WITH_EXPIRE_TIME_PX = "PX";
    private static final String SET_WITH_EXPIRE_TIME_EX = "EX";

    public RedisClient(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }


    public String set(String key, String value) throws JedisException {
        Jedis jedis = null;
        try {
            jedis = new Jedis();
            log.info("----------------------------");
            log.info(jedis.toString());
            log.info(String.valueOf(jedisPool.getNumActive()));
            log.info(String.valueOf(jedisPool.getNumWaiters()));
            log.info(String.valueOf(jedisPool.getNumIdle()));
            log.info("----------------------------");
            return jedis.set(key, value);
        } finally {
            //返还到连接池
            returnResource(jedis);
        }
    }

    public String setEx(String key, int seconds, String value) throws JedisException {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.setex(key, seconds, value);
        } finally {
            //返还到连接池
            returnResource(jedis);
        }
    }

    public Long del(String key) throws JedisException {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            log.info("----------------------------");
            log.info(jedis.toString());
            log.info(String.valueOf(jedisPool.getNumActive()));
            log.info(String.valueOf(jedisPool.getNumWaiters()));
            log.info(String.valueOf(jedisPool.getNumIdle()));
            log.info("----------------------------");
            jedis = jedisPool.getResource();
            return jedis.del(key);
        } finally {
            //返还到连接池
            returnResource(jedis);
        }
    }

    public String get(String key) throws JedisException {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            log.info("###########################");
            log.info(jedisPool.toString());
            log.info(String.valueOf(jedisPool.getNumActive()));
            log.info(String.valueOf(jedisPool.getNumWaiters()));
            log.info(String.valueOf(jedisPool.getNumIdle()));
            log.info("----------------------------");
            return jedis.get(key);
        } finally {
            //返还到连接池
            returnResource(jedis);
            log.info(String.valueOf(jedisPool.getNumActive()));
            log.info(String.valueOf(jedisPool.getNumWaiters()));
            log.info(String.valueOf(jedisPool.getNumIdle()));
            log.info("###########################");
        }
    }

    public void setTime(String key) throws JedisException {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.expire(key, time);
        } finally {
            //返还到连接池
            returnResource(jedis);
        }
    }

    public List<String> lrangeAll(String key) throws JedisException {
        return lrange(key, 0, -1);
    }

    public List<String> lrange(String key, long start, long end) throws JedisException {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.lrange(key, start, end);
        } finally {
            //返还到连接池
            returnResource(jedis);
        }
    }

    public String lindex(String key, long index) throws JedisException {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.lindex(key, index);
        } finally {
            //返还到连接池
            returnResource(jedis);
        }
    }

    public String lindex(String key) throws JedisException {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.lindex(key, 0);
        } finally {
            //返还到连接池
            returnResource(jedis);
        }
    }

    public Long rpush(String key, String... value) throws JedisException {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.rpush(key, value);
        } finally {
            //返还到连接池
            returnResource(jedis);
        }
    }

    public Long lrem(String key, String value) throws JedisException {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.lrem(key, 0, value);
        } finally {
            //返还到连接池
            returnResource(jedis);
        }
    }

    public Boolean hexists(String key, String field) throws JedisException {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.hexists(key, field);
        } finally {
            //返还到连接池
            returnResource(jedis);
        }
    }

    public Long hdel(String key, String field) throws JedisException {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.hdel(key, field);
        } finally {
            //返还到连接池
            returnResource(jedis);
        }
    }

    public Long hset(String key, String field, String value) throws JedisException {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.hset(key, field, value);
        } finally {
            //返还到连接池
            returnResource(jedis);
        }
    }

    public String hget(String key, String field) throws JedisException {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.hget(key, field);
        } finally {
            //返还到连接池
            returnResource(jedis);
        }
    }

    public Map<String, String> hgetAll(String key) throws JedisException {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.hgetAll(key);
        } finally {
            //返还到连接池
            returnResource(jedis);
        }
    }

//    /**
//     * 设置分布式锁
//     *
//     * @param key         需要上锁的key。
//     * @param value       上锁的主人，可以为IP、UUID、HOSTNAME等。
//     * @param millisecond 锁的过期时间。毫秒
//     * @return String "OK"：获取锁成功；"NIL"：获取锁失败。
//     */
//    public String lock(String key, String value, long millisecond) throws JedisException {
//        Jedis jedis = null;
//        try {
//            jedis = jedisPool.getResource();
//            return jedis.set(key, value, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME_PX, millisecond);
//        } finally {
//            returnResource(jedis);
//        }
//    }

    /**
     * 释放分布式锁
     */
    public Object unlock(String lockKey, String requestId) throws JedisException {
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }
        return null;
    }


    private void returnResource(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }
}
