package com.hyt.publish;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EwipPublishApplication {

    public static void main(String[] args) {
        SpringApplication.run(EwipPublishApplication.class, args);
    }
}
