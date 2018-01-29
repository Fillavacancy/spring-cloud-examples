package com.xjz.example.spingcloudeurekaconfig01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//开启配置中心
@EnableConfigServer
@EnableEurekaClient
@SpringBootApplication
public class SpingCloudEurekaConfig01Application {

    public static void main(String[] args) {
        SpringApplication.run(SpingCloudEurekaConfig01Application.class, args);
    }
}
