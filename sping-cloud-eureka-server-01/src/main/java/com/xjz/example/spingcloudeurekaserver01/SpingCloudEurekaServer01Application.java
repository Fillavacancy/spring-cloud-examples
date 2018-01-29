package com.xjz.example.spingcloudeurekaserver01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

//创建服务注册中心
@EnableEurekaServer
@SpringBootApplication
public class SpingCloudEurekaServer01Application {

    public static void main(String[] args) {
        SpringApplication.run(SpingCloudEurekaServer01Application.class, args);
    }
}
