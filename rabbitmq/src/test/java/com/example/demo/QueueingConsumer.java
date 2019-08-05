package com.example.demo;

import com.rabbitmq.client.*;

import java.io.IOException;

public class QueueingConsumer implements Consumer {

    private Channel channel;

    public QueueingConsumer(Channel channel) {
        this.channel = channel;
    }

    @Override
    public void handleConsumeOk(String consumerTag) {
        System.out.println("QueueingConsumer.handleConsumeOk: " + consumerTag);
    }

    @Override
    public void handleCancelOk(String consumerTag) {

    }

    @Override
    public void handleCancel(String consumerTag) throws IOException {

    }

    @Override
    public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {

    }

    @Override
    public void handleRecoverOk(String consumerTag) {

    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        System.out.println("QueueingConsumer.handleDelivery: " + consumerTag);
    }
}
