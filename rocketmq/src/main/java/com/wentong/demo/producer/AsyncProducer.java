package com.wentong.demo.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;

/**
 * 异步发送消息，通过回调函数来判断消息是否送达。
 */
public class AsyncProducer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("async-group");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        producer.setRetryTimesWhenSendAsyncFailed(0);
        final int messageCount = 100;
        CountDownLatch latch = new CountDownLatch(messageCount);
        for (int i = 0; i < messageCount; i++) {
            Message message = new Message("TopicTest", "Tag-async", "OrderID188", ("hello world" + i).getBytes(StandardCharsets.UTF_8));
            producer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    latch.countDown();
                    System.out.println("AsyncProducer.onSuccess");
                }

                @Override
                public void onException(Throwable throwable) {
                    latch.countDown();
                    System.out.println("AsyncProducer.onException");
                    throwable.printStackTrace();
                }
            });
        }
        latch.await();
        producer.shutdown();
    }

}
