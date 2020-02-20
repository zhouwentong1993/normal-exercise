package com.wentong.admin.client.bean;

import org.springframework.context.annotation.Bean;

public class MyBean {

    @Bean
    public MyBean myBean() {
        return new MyBean();
    }

}
