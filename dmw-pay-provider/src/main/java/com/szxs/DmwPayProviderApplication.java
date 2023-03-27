package com.szxs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DmwPayProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DmwPayProviderApplication.class, args);
    }

}
