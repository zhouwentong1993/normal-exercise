package com.wentong.tutorial.lesson4;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

public class EmitLogDirect {
    private static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(EXCHANGE_NAME, "direct");

            for (int i = 0; i < 30; i++) {

                String severity = i > 20 ? "big" : i > 10 ? "mid" : "small";
                String message = "hello " + severity;

                channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes(StandardCharsets.UTF_8));
                System.out.println(" [x] Sent '" + severity + "':'" + message + "'");
            }
        }
    }

}
