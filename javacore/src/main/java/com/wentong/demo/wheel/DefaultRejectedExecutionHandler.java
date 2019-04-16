package com.wentong.demo.wheel;

import java.util.concurrent.ThreadPoolExecutor;

public class DefaultRejectedExecutionHandler implements RejectedExecutionHandler{
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        throw new IllegalStateException("任务：" + r.toString() + "被拒绝");
    }
}
