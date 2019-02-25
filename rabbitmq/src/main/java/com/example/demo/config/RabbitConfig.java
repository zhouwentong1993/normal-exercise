package com.example.demo.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue queue2() {
        return new Queue("queue2");
    }

    @Bean Queue queue1() {
        return new Queue("queue1");
    }
    @Bean
    public Queue queue3() {
        return new Queue("queue3");
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }


    @Bean
    Binding bindingExchange1(Queue queue1, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queue1).to(fanoutExchange);
    }
    @Bean
    Binding bindingExchange2(Queue queue2, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queue2).to(fanoutExchange);
    }
    @Bean
    Binding bindingExchange3(Queue queue3, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queue3).to(fanoutExchange);
    }


}
