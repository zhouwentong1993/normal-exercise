package com.wentong.listener;

import com.wentong.event.AccountCreateEvent;
import lombok.SneakyThrows;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class AccountListener {

    @SneakyThrows
    @EventListener
    @Async
    public void processEvent1(AccountCreateEvent event) {
        TimeUnit.SECONDS.sleep(3);
        System.out.println(event);
    }

}
