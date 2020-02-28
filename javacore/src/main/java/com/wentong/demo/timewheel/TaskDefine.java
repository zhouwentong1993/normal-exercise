package com.wentong.demo.timewheel;

import java.util.concurrent.TimeUnit;

public class TaskDefine extends AbstractTaskDefine {

    public TaskDefine(int delay, TimeUnit timeUnit) {
        super(delay, timeUnit);
    }

    @Override
    public void run() {
        System.out.println("Task 执行");
    }
}
