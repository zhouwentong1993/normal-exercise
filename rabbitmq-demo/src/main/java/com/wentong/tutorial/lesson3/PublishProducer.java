package com.wentong.tutorial.lesson3;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

public class PublishProducer {

    public static void main(String[] args) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection()) {
            Channel channel = connection.createChannel();
            String queueName = "hello";
            channel.queueDeclare(queueName, false, false, false, null);
            channel.exchangeDeclare("demo-exchange1", BuiltinExchangeType.FANOUT);
            channel.queueBind("hello", "demo-exchange1", "");
            for (int i = 0; i < 10; i++) {
                String message = "hello world!" + i;
                channel.basicPublish("", queueName, null, message.getBytes(StandardCharsets.UTF_8));
//                TimeUnit.SECONDS.sleep(1);
                System.out.println(" [x] Sent '" + message + "'");
            }
        }
    }

}
