package com.example.demo.tutorial;

import com.rabbitmq.client.*;

import java.io.IOException;

public class ReceiveLogsDirect2 {

    public static void main(String[] args) throws Exception {

        String[] routingKeys = {"error"};

        Channel channel = RabbitMqUtil.fetchChannel();
        channel.exchangeDeclare("direct_logs", BuiltinExchangeType.DIRECT);
        String queueName = channel.queueDeclare().getQueue();

        for (String routingKey : routingKeys) {
            channel.queueBind(queueName, "direct_logs", routingKey);
            System.out.println("ReceiveLogsDirect2 exchange: direct_logs, queue: " + queueName + ", BindRoutingKey: " + routingKey);
        }

        System.out.println("ReceiveLogsDirect2 Waiting for message");

        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body);
                System.out.println(" [x] Received '" + envelope.getRoutingKey() + "':'" + message + "'");
            }
        };

        channel.basicConsume(queueName, true, consumer);

    }
}
