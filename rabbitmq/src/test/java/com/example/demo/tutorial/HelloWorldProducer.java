package com.example.demo.tutorial;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class HelloWorldProducer {

    public static void main(String[] args) throws Exception {
        // 创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        // 设置 mq 地址
        connectionFactory.setHost("localhost");
        // 创建连接
        Connection connection = connectionFactory.newConnection();
        // 创建 channel
        Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare("hello", false, false, false, null);
        // 将消息发布到队列当中去
        channel.basicPublish("", "hello", null, "hello,world".getBytes());
        System.out.println("SendMessage: hello,world");
        channel.close();
        connection.close();
    }

}
