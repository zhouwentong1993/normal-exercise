package com.wentong.eventbus.demo;


import com.wentong.eventbus.EventBus;

import java.util.concurrent.Executors;

public class Demo1 {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus(Executors.newFixedThreadPool(10));
        eventBus.register(new Event1());
        eventBus.post("Hello eventBus");
    }
}
