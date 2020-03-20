package com.wentong.eventbus.demo;


import com.wentong.eventbus.Subscribe;

public class Event2 {

    @Subscribe
    public void event1(BaseEvent1 baseEvent1) {
        baseEvent1.say();
    }

}
