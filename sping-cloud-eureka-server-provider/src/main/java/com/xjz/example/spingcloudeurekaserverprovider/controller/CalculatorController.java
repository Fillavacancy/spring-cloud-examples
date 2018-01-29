package com.xjz.example.spingcloudeurekaserverprovider.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 服务提供方暴露的数学运算服务
 */
@RestController
public class CalculatorController {

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(CalculatorController.class);

    @Resource
    private DiscoveryClient client;

    @RequestMapping("/add")
    public int add(int a, int b) {
        //加运算
        int result = a + b;
        //输出服务信息
        ServiceInstance instance = client.getLocalServiceInstance();
        logger.info("uri={}，serviceId={}，result={}", instance.getUri(), instance.getServiceId(), result);
        //返回结果
        return result;
    }
}
