package com.xjz.example.spingcloudeurekaserverprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

//开启Feign功能（无需显式@EnableCircuitBreaker，其已含此功能）
@EnableFeignClients
@EnableEurekaClient
@EnableHystrixDashboard // Hystrix 仪表盘
@SpringBootApplication
public class SpingCloudEurekaServerProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpingCloudEurekaServerProviderApplication.class, args);
    }
}
