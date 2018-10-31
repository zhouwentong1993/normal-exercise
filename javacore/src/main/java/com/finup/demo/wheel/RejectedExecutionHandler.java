package com.finup.demo.wheel;

import java.util.concurrent.ThreadPoolExecutor;

public interface RejectedExecutionHandler {

    /**
     * 拒绝策略
     */
    void rejectedExecution(Runnable r, ThreadPoolExecutor executor);

}
