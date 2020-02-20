package com.wentong.admin.client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("hello")
    public String hello() {
        return "hello world";
    }
    @GetMapping("exception")
    public void exception() {
        throw new UnsupportedOperationException();
    }


}
