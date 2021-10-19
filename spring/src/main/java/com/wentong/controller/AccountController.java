package com.wentong.controller;

import com.wentong.event.AccountCreateEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    private final ApplicationEventPublisher publisher;

    public AccountController(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @GetMapping("account")
    public String account() {
        long start = System.currentTimeMillis();
        AccountCreateEvent jack = new AccountCreateEvent(this, "1", "jack");
        publisher.publishEvent(jack);
        return "ok" + (System.currentTimeMillis() - start);
    }


}
