package com.wentong.eventbus.demo;


import com.wentong.eventbus.EventBus;

public class Demo1 {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        eventBus.register(new Event1());
        eventBus.register(new Event2());
        eventBus.post(new BaseEvent1());
    }
}
