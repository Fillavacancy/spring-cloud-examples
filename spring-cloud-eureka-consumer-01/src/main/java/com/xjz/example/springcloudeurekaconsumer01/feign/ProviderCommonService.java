package com.xjz.example.springcloudeurekaconsumer01.feign;

import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//绑定該接口到CalculatorServer服务，并通知Feign组件对该接口进行代理（不需要编写接口实现）
// fallback 和 fallbackFactory 都是实现断路器的功能
// fallbackFactory 是 fallback 的增强版 可以捕获到异常
@FeignClient(value = "sping-cloud-eureka-server-provider", fallbackFactory = ProviderCommonService.HystrixCalculatorService.class)
public interface ProviderCommonService {
    ////@PathVariable這種也是支持的
    //@RequestMapping(value="/add/{a}", method=RequestMethod.GET)
    //int myadd(@PathVariable("a") int a, @RequestParam("b") int b);

    //通过SpringMVC的注解来配置所綁定的服务下的具体实现
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public int myadd(@RequestParam("a") int a, @RequestParam("b") int b);

    /**
     * 这里采用和SpringCloud官方文档相同的做法，把fallback类作为内部类放入Feign接口中
     * http://cloud.spring.io/spring-cloud-static/Camden.SR6/#spring-cloud-feign-hystrix
     * （也可以外面独立定义该类，个人觉得没必要，这种东西写成内部类最合适）
     */
    @Component
    class HystrixCalculatorService implements FallbackFactory<ProviderCommonService> {

        private static final Logger logger = LoggerFactory.getLogger(HystrixCalculatorService.class);

        @Override
        public ProviderCommonService create(Throwable throwable) {
            logger.info("fallback reason was: {} ", throwable.getMessage());
            System.out.println(throwable.getMessage());
            return (a, b) -> {
                return -999;
            };
        }
    }
}