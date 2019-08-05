package com.example.demo;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RabbitMqDelayQueueTest {
    public static void main(String[] args){
        try {

            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            Map<String, Object> param = new HashMap<>();
            param.put("x-delayed-type", "direct");
            channel.exchangeDeclare("delay_exchange123", "x-delayed-message", true,
                    false, param);
            Map<String, Object> headers = new HashMap<>();
            Date now = new Date();
            Date timeToPublish = new Date("2019/08/05,16:37:00");

            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

            String readyToPushContent = "publish at " + sf.format(now)
                    + " \t deliver at " + sf.format(timeToPublish);

            headers.put("x-delay", timeToPublish.getTime() - now.getTime());

            AMQP.BasicProperties.Builder props = new AMQP.BasicProperties.Builder()
                    .headers(headers);
            channel.basicPublish("x-exchange", "test", props.build(),
                    readyToPushContent.getBytes());

            // 关闭频道和连接
            channel.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
