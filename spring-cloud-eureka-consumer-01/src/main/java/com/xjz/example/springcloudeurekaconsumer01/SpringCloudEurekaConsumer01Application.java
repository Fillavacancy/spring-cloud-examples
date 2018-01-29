package com.xjz.example.springcloudeurekaconsumer01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SpringCloudEurekaConsumer01Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudEurekaConsumer01Application.class, args);
    }
}
