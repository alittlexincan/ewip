package com.hyt.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableDiscoveryClient
//@EnableFeignClients
public class EwipClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(EwipClientApplication.class, args);
    }
}
