package com.wentong.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestThreadLocalOOM {

    private static final int COUNT = 10000;

    public static void main(String[] args) throws Exception{

        ThreadLocal<List<User>> threadLocal = new ThreadLocal<>();

        ExecutorService executorService = Executors.newFixedThreadPool(500);
        for (int i = 0; i < 500; i++) {
            executorService.execute(() -> {
                threadLocal.set(getBigData());
                Thread thread = Thread.currentThread();
                System.out.println("Thread is:" + thread);
                threadLocal.remove();
            });
            TimeUnit.SECONDS.sleep(1);
        }
    }

    private static List<User> getBigData() {
        List<User> list = new ArrayList<>(COUNT);

        for (int i = 0; i < COUNT; i++) {
            list.add(new User("name:" + i, "password:" + i, "address:" + i));
        }
        return list;
    }

    static class User {
        String name;
        String password;
        String address;

        public User(String name, String password, String address) {
            this.name = name;
            this.password = password;
            this.address = address;
        }
    }
}
