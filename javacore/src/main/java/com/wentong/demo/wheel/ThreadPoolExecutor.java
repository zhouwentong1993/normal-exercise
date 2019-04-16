package com.wentong.demo.wheel;

public class ThreadPoolExecutor {
    private int corePoolSize;
    private int maximumPoolSize;
    private BlockingQueue<Runnable> workQueue;
    private RejectedExecutionHandler rejectedExecutionHandler;
    private RejectedExecutionHandler defaultRejectedExecutionHandler = new DefaultRejectedExecutionHandler();

    public ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler rejectedExecutionHandler) {
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.workQueue = workQueue;
        this.rejectedExecutionHandler = rejectedExecutionHandler;
    }

    public ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, BlockingQueue<Runnable> workQueue) {
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.workQueue = workQueue;
        this.rejectedExecutionHandler = defaultRejectedExecutionHandler;
    }
}
