package com.example.demo.tutorial;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

public class ReceiveLogs1 {

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtil.fetchChannel();
        channel.exchangeDeclare("logs", "fanout");
        channel.queueDeclare("test_fanout1", true, false, false, null);

        String queueName = "test_fanout1";
        channel.queueBind(queueName, "logs", "");
        System.out.println("Waiting for message");
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body);
                System.out.println("Received: " + message);
            }
        };
        channel.basicConsume(queueName, true, consumer);
    }

}
