package com.example.demo.tutorial;

import com.rabbitmq.client.*;

import java.io.IOException;

public class HelloWorldConsumer {
    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("hello", false, false, false, null);
        System.out.println("Waiting for message.");
        // 向 channel 注册事件
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body);
                System.out.println("Received: " + message);
            }
        };
        // 设置自动应答
        channel.basicConsume("hello", true, defaultConsumer);
    }
}
