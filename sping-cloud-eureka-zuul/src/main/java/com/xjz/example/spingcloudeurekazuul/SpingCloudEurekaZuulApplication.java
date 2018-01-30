package com.xjz.example.spingcloudeurekazuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

//注意不是@EnableZuulServer
@EnableZuulProxy
//注意这里使用了更加简化的@SpringCloudApplication
@SpringCloudApplication
@EnableAutoConfiguration
public class SpingCloudEurekaZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpingCloudEurekaZuulApplication.class, args);
    }
}
