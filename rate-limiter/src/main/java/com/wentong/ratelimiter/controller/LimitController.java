package com.wentong.ratelimiter.controller;

import com.wentong.ratelimiter.limiter.Limiter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("limit")
public class LimitController {

    private final Limiter limiter;

    public LimitController(@Qualifier("slidingWindowsLimiter") Limiter limiter) {
        this.limiter = limiter;
    }

    @GetMapping("check/{key}")
    public String check(@PathVariable String key) {
        if (limiter.acquire(key)) {
            System.out.println("acquired!");
            return "ok!";
        } else {
            System.out.println("not acquired");
            return "not ok!";
        }
    }

}
