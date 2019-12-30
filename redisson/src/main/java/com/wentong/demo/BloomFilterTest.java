package com.wentong.demo;

import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;

import java.util.concurrent.Executors;

public class BloomFilterTest {
    public static void main(String[] args) {
        Config config = new Config();
        config.setTransportMode(TransportMode.NIO);
        config.useClusterServers()
                .addNodeAddress("http://127.0.0.1:7000")
                .addNodeAddress("http://127.0.0.1:7001")
                .addNodeAddress("http://127.0.0.1:7002")
                .addNodeAddress("http://127.0.0.1:7003")
                .addNodeAddress("http://127.0.0.1:7004")
                .addNodeAddress("http://127.0.0.1:7005");

        config.setExecutor(Executors.newFixedThreadPool(2000));
        // 指定分布式锁中未添加超时时间的情况，默认值
        config.setLockWatchdogTimeout(30000);

        RedissonClient redisson = Redisson.create(config);
        RBloomFilter<Object> bloomFilter = redisson.getBloomFilter("testb");
        bloomFilter.tryInit(55000000L, 0.03);
        bloomFilter.add(111);
        bloomFilter.add("bbb");
        System.out.println(bloomFilter.count());
        boolean aaa = bloomFilter.contains(111);
        boolean sss = bloomFilter.contains("sss");
        System.out.println(aaa);
        System.out.println(sss);
    }
}
