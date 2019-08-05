package com.example.demo.tutorial;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class WorkQueueWorker1 {

    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMqUtil.getChannelOfLocalhost();
        channel.queueDeclare("task_queue", true, false, false, null);
        // 每次获取一个
        channel.basicQos(1);

        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body);
                System.out.println("Worker1 Received: " + message);
                try {
                    doWork();
                } finally {
                    System.out.println("Worker1 Done");
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }
            }
        };
        channel.basicConsume("task_queue", false, consumer);

    }

    private static void doWork() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
