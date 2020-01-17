package com.wentong.hystrix.spring.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@RestController
public class DemoController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/demo1")
    @HystrixCommand(ignoreExceptions = {HystrixBadRequestException.class}, commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100"), @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "100")

    }, threadPoolProperties = {@HystrixProperty(name = "coreSize", value = "10")})
    public String demo1() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        return restTemplate.getForObject("http://localhost:8080/demo2", String.class);
    }

    @GetMapping("/demo2")
    @HystrixCommand(ignoreExceptions = {HystrixBadRequestException.class}, commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100"), @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "100")

    }, threadPoolProperties = {@HystrixProperty(name = "coreSize", value = "10")})
    public String demo2() {
        return "demo2";
    }

}
