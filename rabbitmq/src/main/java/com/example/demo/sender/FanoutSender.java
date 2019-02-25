package com.example.demo.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FanoutSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        for (int i = 0; i < 10; i++) {
            String context = "hi, fanout msg " + i;
            System.out.println("Sender : " + context);
            this.rabbitTemplate.convertAndSend("fanoutExchange","", context);
        }
    }

}