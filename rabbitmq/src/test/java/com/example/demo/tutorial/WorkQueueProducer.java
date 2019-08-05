package com.example.demo.tutorial;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

public class WorkQueueProducer {
    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtil.fetchChannel();
        for (int i = 0; i < 10; i++) {
            String message = "hello" + i;
            channel.basicPublish("", "task_queue", MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
            System.out.println("Send: " + message);
        }
        RabbitMqUtil.close();
    }
}
