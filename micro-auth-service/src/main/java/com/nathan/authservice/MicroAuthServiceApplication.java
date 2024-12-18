package com.nathan.authservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroAuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroAuthServiceApplication.class, args);
    }

}
