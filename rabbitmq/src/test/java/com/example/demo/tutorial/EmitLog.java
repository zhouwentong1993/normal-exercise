package com.example.demo.tutorial;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

public class EmitLog {
    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtil.fetchChannel();
        channel.exchangeDeclare("logs", BuiltinExchangeType.FANOUT);
        for (int i = 0; i < 10; i++) {
            String message = "hello,fanout: " + i;
            channel.basicPublish("logs", "", null, message.getBytes());
            System.out.println("Send: " + message);
        }
        RabbitMqUtil.close();
    }
}
