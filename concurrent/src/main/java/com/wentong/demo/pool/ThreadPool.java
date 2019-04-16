package com.wentong.demo.pool;

/**
 * Created by zhouwentong on 2018/5/16.
 */
public interface ThreadPool<Job extends Runnable> {

    /**
     * 执行一个指定任务
     * @param job 任务
     */
    void execute(Job job);

    /**
     * 终止任务
     */
    void shutdown();

    /**
     * 增加 worker
     * @param num 要增加的数量
     */
    void addWorkers(int num);

    /**
     * 移除 worker
     * @param num 要移除的数量
     */
    void removeWorkers(int num);

    /**
     * 获取当前 pool 中的 worker 数量
     * @return worker 数量
     */
    int getWorkerNumber();
}
