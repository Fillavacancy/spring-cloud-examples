package com.xjz.example.springcloudeurekaconsumer02.controller;

import com.xjz.example.springcloudeurekaconsumer02.feign.ProviderCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 服务调用方
 */
@RestController
@RequestMapping("/demo/feign")
public class ConsumerController {

    @Autowired
    private ProviderCommonService providerCommonService;

    @RequestMapping("/toadd")
    public int toadd(HttpServletRequest request, int a, int b) {
        System.out.println("-----------------" + request.getRequestedSessionId());
        return providerCommonService.myadd(a, b);
    }
}