package com.wentong.demo.timewheel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    // 标识当前在第几圈
    private static int currentWheel = 0;

    // 标识当前在第几个位置上
    private static int currentPos = 0;

    // 一圈有多少个单位
    private static final int TICKS_PER_WHEEL = 60;

    // 保存任务的容器
    static List<Set<AbstractTaskDefine>> container = new ArrayList<>(TICKS_PER_WHEEL);

    static {
        for (int i = 0; i < 60; i++) {
            container.add(new HashSet<>());
        }
    }

    public static void main(String[] args) throws Exception {
        printTime();
        scheduledFindTask();
        TimeUnit.SECONDS.sleep(4);
        System.out.println("在第 4 秒放入一个 15 秒后响应的任务，第 19 秒响应");
        addTask(new TaskDefine(15, TimeUnit.SECONDS));
        TimeUnit.SECONDS.sleep(7);
        System.out.println("在第 11 秒放入一个 60 秒后响应的任务，第 71 秒响应");
        addTask(new TaskDefine(60, TimeUnit.SECONDS));
    }

    private static void scheduledFindTask() {
        try {

            ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
            executorService.scheduleAtFixedRate(() -> {
                System.out.println("Main.scheduledFindTask");
                Set<AbstractTaskDefine> abstractTaskDefines = container.get(currentPos);
                if (abstractTaskDefines != null && !abstractTaskDefines.isEmpty()) {
                    abstractTaskDefines.forEach(abstractTaskDefine -> {
                        if (abstractTaskDefine.getWheels() == currentWheel) {
                            abstractTaskDefine.run();
                            abstractTaskDefines.remove(abstractTaskDefine);
                        }
                    });
                }
                if (currentPos != TICKS_PER_WHEEL) {
                    currentPos++;
                } else {
                    currentPos = 0;
                    currentWheel++;
                }
            }, 0, 1, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addTask(AbstractTaskDefine abstractTaskDefine) {
        int delayInSeconds = (int) abstractTaskDefine.getTimeUnit().convert(abstractTaskDefine.getDelay(), TimeUnit.SECONDS);
        int wheel = (currentPos + delayInSeconds) / TICKS_PER_WHEEL;
        int pos = (currentPos + delayInSeconds) % TICKS_PER_WHEEL;
        abstractTaskDefine.setWheels(wheel);
        Set<AbstractTaskDefine> taskDefines = container.get(pos);
        if (taskDefines == null) {
            Set<AbstractTaskDefine> abstractTaskDefines = new HashSet<>();
            abstractTaskDefines.add(abstractTaskDefine);
            container.set(pos, abstractTaskDefines);
        } else {
            taskDefines.add(abstractTaskDefine);
        }
    }

    private static void printTime() {
        AtomicInteger i = new AtomicInteger();
        new Thread(() -> {
            for (; ; ) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(i.incrementAndGet());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
