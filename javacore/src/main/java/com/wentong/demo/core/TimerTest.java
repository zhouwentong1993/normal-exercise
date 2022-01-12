package com.wentong.demo.core;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {

    public static void main(String[] args) {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ":" + System.currentTimeMillis());
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 3 * 1000L, 3 * 1000L);
    }

}
