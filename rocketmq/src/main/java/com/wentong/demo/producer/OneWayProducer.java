package com.wentong.demo.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.nio.charset.StandardCharsets;

/**
 * 测试单向发送生产者
 */
public class OneWayProducer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("one-way");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        for (int i = 0; i < 100; i++) {
            Message message = new Message("TopicTest", "tag-oneway", ("hello rocketmq" + i).getBytes(StandardCharsets.UTF_8));
            producer.sendOneway(message);
        }
        producer.shutdown();
    }

}
