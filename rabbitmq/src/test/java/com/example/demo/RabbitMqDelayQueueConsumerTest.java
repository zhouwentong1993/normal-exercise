package com.example.demo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RabbitMqDelayQueueConsumerTest {

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
            System.out.println("now:\t" + sf.format(new Date()));
        };

        channel.queueDeclare("delay_queue", true, false, false, null);
        channel.queueBind("delay_queue", "x-exchange", "");
        channel.basicConsume("delay_queue", true, deliverCallback, consumerTag -> {
        });
    }

}
