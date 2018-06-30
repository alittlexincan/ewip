package com.hyt.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EwipEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EwipEurekaApplication.class, args);
    }
}
