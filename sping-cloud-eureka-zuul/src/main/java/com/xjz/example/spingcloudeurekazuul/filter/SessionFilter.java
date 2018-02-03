/**
 * FileName: SessionFilter
 * Author:   xiangjunzhong
 * Date:     2018/1/29 17:26
 * Description:
 */
package com.xjz.example.spingcloudeurekazuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author xiangjunzhong
 * @create 2018/1/29 17:26
 * @since 1.0.0
 */
@Component
public class SessionFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        return (boolean) ctx.get("isSuccess");
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest req = ctx.getRequest();
        System.out.println("-----------------" + req.getRequestedSessionId());
        System.out.println("-----------------" + req.getSession().getId());
        Enumeration<String> et = req.getHeaderNames();
        System.out.println("\r\n------------------------输出Header参数开始---------------------------------------");
        while (et.hasMoreElements()) {
            String name = et.nextElement();
            String value = req.getHeader(name);
            System.out.println(name + ":" + value);
        }
        Map<String, String[]> ps = req.getParameterMap();
        for (String key : ps.keySet()) {
            System.out.print(key + ":\t");
            String[] value = ps.get(key);
            if (value != null)
                for (int i = 0; i < value.length; i++) {
                    System.out.print(value[i] + "\t");
                }
            System.out.print("\r\n");
        }
        System.out.println("\r\n------------------------输出Header参数结束---------------------------------------");
        // 将请求session同步到被调用路由微服务
        ctx.addZuulRequestHeader("Cookie", "JSESSIONID=" + req.getSession().getId());
        System.out.println("\r\n---------------------------------------------------------------");
        return null;
    }
}