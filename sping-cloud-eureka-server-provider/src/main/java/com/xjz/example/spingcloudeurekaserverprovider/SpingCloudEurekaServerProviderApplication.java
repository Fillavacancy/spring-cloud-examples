package com.xjz.example.spingcloudeurekaserverprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SpingCloudEurekaServerProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpingCloudEurekaServerProviderApplication.class, args);
    }
}
