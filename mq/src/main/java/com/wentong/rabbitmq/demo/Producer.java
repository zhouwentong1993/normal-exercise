package com.wentong.rabbitmq.demo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Created by zhouwentong on 2018/5/24.
 * RabbitMq 测试
 */
public class Producer {
    public static void main(String[] args) throws Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("test", false, false, false, null);
        String message = "Hello world";
        channel.basicPublish("", "test", null, message.getBytes());
        System.out.println("publish :" + message);
        channel.close();
        connection.close();
    }
}
