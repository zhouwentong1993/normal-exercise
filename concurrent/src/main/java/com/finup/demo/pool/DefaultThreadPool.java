package com.finup.demo.pool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhouwentong on 2018/5/16.
 * 默认 ThreadPool
 */
public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {

    private static final int MAX_WORKER_SIZE = 10;
    private static final int MIN_WORKER_SIZE = 1;
    private static final int DEFAULT_WORKER_SIZE = 5;
    private final LinkedList<Job> jobs = new LinkedList<>();
    private List<Worker> workers = Collections.synchronizedList(new ArrayList<>());
    private int workerNumber;
    private AtomicInteger number = new AtomicInteger();

    public DefaultThreadPool(int workerNumber) {
        this.workerNumber = workerNumber > MAX_WORKER_SIZE ? MAX_WORKER_SIZE : workerNumber < MIN_WORKER_SIZE ? MIN_WORKER_SIZE : workerNumber;
        init(workerNumber);
    }

    public DefaultThreadPool() {
        this.workerNumber = DEFAULT_WORKER_SIZE;
        init(workerNumber);
    }

    private void init(int poolSize) {
        for (int i = 0; i < poolSize; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            Thread thread = new Thread(worker, "thread-" + number.getAndIncrement());
            thread.start();
        }
    }


    @Override
    public void execute(Job job) {
        if (job != null) {
            synchronized (jobs) {
                jobs.add(job);
                // 唤醒一个监听对象，不要唤醒多个，因为一个任务只需要任意一个 worker 就可以完成，全部唤醒耗费性能
                jobs.notify();
            }
        }
    }

    @Override
    public void shutdown() {
        for (Worker worker : workers) {
            worker.shutdown();
        }
    }

    @Override
    public void addWorkers(int num) {
        int increment = this.workerNumber + num > MAX_WORKER_SIZE ? MAX_WORKER_SIZE - workerNumber : num;
        init(increment);
        this.workerNumber = workerNumber + increment;
    }

    @Override
    public void removeWorkers(int num) {

        if (num < 0) {
            throw new IllegalArgumentException("num less than 0");
        }
        int decrement = workerNumber - num < MIN_WORKER_SIZE ? workerNumber - MIN_WORKER_SIZE : num;
        for (int i = 0; i < decrement; i++) {
            if (workers.remove(workers.get(i))) {
                workers.get(i).shutdown();
            }
        }
        this.workerNumber = workerNumber - decrement;
    }

    @Override
    public int getWorkerNumber() {
        return this.workerNumber;
    }

    class Worker implements Runnable {

        private volatile boolean running = true;

        Job job = null;

        @Override
        public void run() {
            while (running) {
                synchronized (jobs) {
                    if (jobs.isEmpty()) {
                        try {
                            jobs.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    job = jobs.removeFirst();
                }
                if (job != null) {
                    job.run();
                }
            }
        }

        private void shutdown() {
            this.running = false;
        }
    }
}

