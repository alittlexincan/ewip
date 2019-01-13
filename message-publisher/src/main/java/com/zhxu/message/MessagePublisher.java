package com.zhxu.message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MessagePublisher {
    public static void main(String[] args) {
        SpringApplication.run(MessagePublisher.class, args);
    }
}
