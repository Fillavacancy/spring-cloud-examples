package com.xjz.example.springcloudeurekaconsumer01.controller;

import com.xjz.example.springcloudeurekaconsumer01.feign.ProviderCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;

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
        System.out.println("-----------------" + request.getSession().getId());
        Enumeration<String> et = request.getHeaderNames();
        System.out.println("\r\n---------------------------------------------------------------");
        while (et.hasMoreElements()) {
            String name = et.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + ":" + value);
        }
        Map<String, String[]> ps = request.getParameterMap();
        for (String key : ps.keySet()) {
            System.out.print(key + ":\t");
            String[] value = ps.get(key);
            if (value != null)
                for (int i = 0; i < value.length; i++) {
                    System.out.print(value[i] + "\t");
                }
            System.out.print("\r\n");
        }
        System.out.println("Cookie" + "JSESSIONID=" + request.getSession().getId());
        System.out.println("\r\n---------------------------------------------------------------");
        return providerCommonService.myadd(a, b);
    }
}