package com.xjz.example.springcloudeurekaconsumer03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SpringCloudEurekaConsumer03Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudEurekaConsumer03Application.class, args);
    }
}
