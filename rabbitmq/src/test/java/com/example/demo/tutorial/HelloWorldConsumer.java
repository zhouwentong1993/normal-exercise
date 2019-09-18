package com.example.demo.tutorial;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;

import java.util.concurrent.TimeUnit;

public class HelloWorldConsumer {
    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("hello", false, false, false, null);
        System.out.println("Waiting for message.");

        // 拉模式
        while (true) {
            GetResponse resp = channel.basicGet("hello", true);
            if (resp == null) {
                System.out.println("Get Nothing!");
                TimeUnit.MILLISECONDS.sleep(1000);
            } else {
                String message = new String(resp.getBody(), "UTF-8");
                System.out.printf(" [    %2$s<===](%1$s) %3$s\n", "Receiver", "hello", message);
                TimeUnit.MILLISECONDS.sleep(500);
            }
        }

        // 推模式
//        // 向 channel 注册事件
//        DefaultConsumer defaultConsumer = new DefaultConsumer(channel){
//            @Override
//            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                String message = new String(body);
//                System.out.println("Received: " + message);
//            }
//        };
//        // 设置自动应答
//        channel.basicConsume("hello", true, defaultConsumer);
    }
}
