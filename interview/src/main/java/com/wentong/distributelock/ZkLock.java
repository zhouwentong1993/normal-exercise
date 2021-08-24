package com.wentong.distributelock;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;

public class ZkLock {

    private static final String lockPath = "/distributed-lock";

    // ZooKeeper 服务地址, 单机格式为:(127.0.0.1:2181), 集群格式为:(127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183)
    private static final String connectString = "127.0.0.1:2181";
    // Curator 客户端重试策略
    private static RetryPolicy retry;
    // Curator 客户端对象
    private static CuratorFramework client;
    // client2 用户模拟其他客户端
    private static CuratorFramework client2;

    public static void main(String[] args) throws Exception {
        retry = new ExponentialBackoffRetry(1000, 3);
        // 创建一个客户端, 60000(ms)为 session 超时时间, 15000(ms)为链接超时时间
        client = CuratorFrameworkFactory.newClient(connectString, 60000, 15000, retry);
        client2 = CuratorFrameworkFactory.newClient(connectString, 60000, 15000, retry);
        // 创建会话
        client.start();
        client2.start();
        InterProcessLock lock1 = new InterProcessSemaphoreMutex(client, lockPath);
        InterProcessLock lock2 = new InterProcessSemaphoreMutex(client2, lockPath);

        lock1.acquire();
        boolean acquire = lock2.acquire(2, TimeUnit.SECONDS);
        System.out.println(acquire);
        lock1.release();
        System.out.println(lock2.acquire(2, TimeUnit.SECONDS));
    }
}
