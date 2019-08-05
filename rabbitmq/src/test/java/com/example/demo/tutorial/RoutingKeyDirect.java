package com.example.demo.tutorial;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

public class RoutingKeyDirect {

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtil.fetchChannel();
        channel.exchangeDeclare("direct_logs", BuiltinExchangeType.DIRECT);
        String[] routingKeys = {"info", "warn", "error"};
        for (String routingKey : routingKeys) {
            String message = "Send message level: " + routingKey;
            channel.basicPublish("direct_logs", routingKey, null, message.getBytes());
            System.out.println("Sent " + routingKey + ": " + message);
        }
        RabbitMqUtil.close();
    }
}
