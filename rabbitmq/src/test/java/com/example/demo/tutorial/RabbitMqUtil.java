package com.example.demo.tutorial;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public final class RabbitMqUtil {

    private RabbitMqUtil(){}

    static Channel channel = null;
    static Connection connection;

    public static Channel getChannelOfLocalhost() {
        try {
            // 创建连接工厂
            ConnectionFactory connectionFactory = new ConnectionFactory();
            // 设置 mq 地址
            connectionFactory.setHost("localhost");
            // 创建连接
            connection = connectionFactory.newConnection();
            // 创建 channel
            channel = connection.createChannel();
            return channel;
        } catch (Exception e) {
            System.out.println(e);
            System.exit(1);
            return null;
        }
    }

    public static void close() {
        if (channel != null) {
            try {
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
