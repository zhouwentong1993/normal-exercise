package com.wentong.sample;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.nio.charset.StandardCharsets;

public class SampleProducer {

    public static void main(String[] args) throws Exception {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("demo-queue", true, false, false, null);
        channel.queueBind("demo-queue", "demo-exchange", "demo-routingkey");

        channel.basicPublish("demo-exchange", "demo-routingkey", MessageProperties.TEXT_PLAIN, "hello world!".getBytes(StandardCharsets.UTF_8));
        channel.close();
        connection.close();

    }

}
