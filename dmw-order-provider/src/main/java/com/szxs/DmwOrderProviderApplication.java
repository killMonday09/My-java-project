package com.szxs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DmwOrderProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DmwOrderProviderApplication.class, args);
    }

}
