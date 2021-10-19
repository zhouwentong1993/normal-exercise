package com.wentong.event;

import org.springframework.context.ApplicationEvent;

public class AccountCreateEvent extends ApplicationEvent {
    private String id;
    private String name;

    public AccountCreateEvent(Object source, String id,String name) {
        super(source);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
