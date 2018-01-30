/**
 * FileName: IPFilter
 * Author:   xiangjunzhong
 * Date:     2018/1/29 14:40
 * Description: 利用Zuul的过滤器，可以实现对外服务的安全控制 IP 限制
 */
package com.xjz.example.spingcloudeurekazuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈利用Zuul的过滤器，可以实现对外服务的安全控制 IP 限制〉
 *
 * @author xiangjunzhong
 * @create 2018/1/29 14:40
 * @since 1.0.0
 */
@Component
public class IPFilter extends ZuulFilter {

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(IPFilter.class);

    /**
     * 返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型
     * 具体如下：
     * pre：路由之前
     * routing：路由之时
     * post： 路由之后
     * error：发送错误调用
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤的顺序
     * 通过int值来定义过滤器的执行顺序
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 这里可以写逻辑判断，是否要过滤，本文true,永远过滤。
     * 设置该过滤器总是生效，即总是执行拦截请求
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体逻辑。
     * 可用很复杂，包括查sql，nosql去判断该请求到底有没有权限访问。
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        logger.info(String.format("收到 %s 请求 %s", request.getMethod(), request.getRequestURL().toString()));
        String ipAddr = this.getIpAddr(request);
        logger.info("请求IP地址为：[{}]", ipAddr);
        //配置本地IP白名单，生产环境可放入数据库或者redis中
        List<String> ips = new ArrayList<String>();
        ips.add("172.0.0.1");
        if (ips.contains(ipAddr)) {
            logger.info("IP地址校验不通过！！！");
            ctx.setResponseStatusCode(401);
            ctx.setSendZuulResponse(false);
            ctx.setResponseBody("IpAddr is forbidden!");
            ctx.set("isSuccess", false);
            return null;
        }
        ctx.set("isSuccess", true);
        logger.info("IP校验通过。");
        return null;
    }

    /**
     * 获取Ip地址
     *
     * @param request
     * @return
     */
    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}