package com.wentong.microservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MicroServicesApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(MicroServicesApplication.class);

	@Autowired
	private RedisService redisService;

	public static void main(String[] args) {
		SpringApplication.run(MicroServicesApplication.class, args);
	}

	@GetMapping("get/{name}")
	public String get(@PathVariable String name) {
		LOGGER.info("hello,{}", name);
		return redisService.get(name);
	}

	@GetMapping("set/{name}")
	public String set(@PathVariable String name) {
		redisService.set(name, name);
		return "ok";
	}

}
