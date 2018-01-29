package com.xjz.example.springcloudeurekaconsumer02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SpringCloudEurekaConsumer02Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudEurekaConsumer02Application.class, args);
    }
}
