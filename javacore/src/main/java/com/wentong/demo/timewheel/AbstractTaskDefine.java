package com.wentong.demo.timewheel;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.concurrent.TimeUnit;

public abstract class AbstractTaskDefine implements Runnable {

    // 所属第几圈
    private int wheels;

    // 多少个时间单位后触发
    private int delay;

    // 时间单位是什么
    private TimeUnit timeUnit;

    public AbstractTaskDefine(int delay, TimeUnit timeUnit) {
        this.delay = delay;
        this.timeUnit = timeUnit;
    }

    public int getWheels() {
        return wheels;
    }

    public void setWheels(int wheels) {
        this.wheels = wheels;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

    @Override
    public void run() {
        // do noting
    }

}
