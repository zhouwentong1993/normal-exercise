package com.finup.rabbitmq.demo;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Created by zhouwentong on 2018/5/24.
 */
public class MyConsumer {
    public static void main(String[] args) throws Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("test", false, false, false, null);
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("Received message: " + message);
            }
        };
        channel.basicConsume("test", true, consumer);
        channel.close();
        connection.close();
    }
}
