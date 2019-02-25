package com.example.demo.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RabbitListener(queues = "queue2")
@Component
public class Queue2Listener {

    @RabbitHandler
    public void process(String message) {
        System.out.println("q2:" + message);
    }
}
