package com.wentong.eventbus.demo;


import com.wentong.eventbus.Subscribe;

public class Event1 {

    @Subscribe
    public String event1(String name) {
        System.out.println(name);
        return name;
    }

    @Subscribe
    public void event2(BaseEvent baseEvent) {
        baseEvent.say();
    }



}
