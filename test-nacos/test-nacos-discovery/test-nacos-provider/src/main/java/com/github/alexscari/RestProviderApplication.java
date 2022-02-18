package com.github.alexscari;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author wusd
 */
@SpringBootApplication
@EnableDiscoveryClient
public class RestProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestProviderApplication.class, args);
    }

}
