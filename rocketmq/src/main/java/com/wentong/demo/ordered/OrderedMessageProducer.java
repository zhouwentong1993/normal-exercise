package com.wentong.demo.ordered;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class OrderedMessageProducer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("ordered-producer");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();

        String[] tags = {"TagA", "TagB", "TagC"};

        List<OrderStep> orderSteps = orderSteps();
        for (int i = 0; i < orderSteps.size(); i++) {
            Message message = new Message("TopicTest", tags[i % tags.length], "KEY" + i, ("Hello RocketMQ" + orderSteps.get(i)).getBytes(StandardCharsets.UTF_8));
            // 选择队列路由规则，强制消息发送到一个队列里面去。第三个参数是标识 key，这里用订单号做唯一标识。
            SendResult result = producer.send(message, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                    long id = (long) o;
                    long index = id % list.size();
                    return list.get((int) index);
                }
            }, orderSteps.get(i).getOrderId());
            System.out.println(result);
            System.out.println(result.getMessageQueue().getQueueId());
            System.out.println("------------------");
        }

    }

    private static List<OrderStep> orderSteps() {
        List<OrderStep> orders = new ArrayList<>();
        orders.add(new OrderStep("创建订单", 1));
        orders.add(new OrderStep("付款", 1));
        orders.add(new OrderStep("推送消息", 1));
        orders.add(new OrderStep("完成", 1));
        orders.add(new OrderStep("创建订单", 2));
        orders.add(new OrderStep("付款", 2));
        orders.add(new OrderStep("推送消息", 2));
        orders.add(new OrderStep("完成", 2));
        orders.add(new OrderStep("创建订单", 3));
        orders.add(new OrderStep("付款", 3));
        orders.add(new OrderStep("推送消息", 3));
        orders.add(new OrderStep("完成", 3));
        orders.add(new OrderStep("创建订单", 4));
        orders.add(new OrderStep("付款", 4));
        orders.add(new OrderStep("推送消息", 4));
        orders.add(new OrderStep("完成", 4));
        orders.add(new OrderStep("创建订单", 5));
        orders.add(new OrderStep("付款", 5));
        orders.add(new OrderStep("推送消息", 5));
        orders.add(new OrderStep("完成", 5));
        orders.add(new OrderStep("创建订单", 6));
        orders.add(new OrderStep("付款", 6));
        orders.add(new OrderStep("推送消息", 6));
        orders.add(new OrderStep("完成", 6));

        return orders;
    }

    @Data
    @AllArgsConstructor
    static class OrderStep {
        String desc;
        long orderId;
    }

}
