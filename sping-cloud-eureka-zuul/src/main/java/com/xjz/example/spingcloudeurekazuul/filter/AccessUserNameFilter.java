/**
 * FileName: AccessUserNameFilter
 * Author:   xiangjunzhong
 * Date:     2018/1/29 14:52
 * Description: 利用Zuul的过滤器，可以实现对外服务的安全控制 用户名 限制
 */
package com.xjz.example.spingcloudeurekazuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * 〈一句话功能简述〉<br>
 * 〈利用Zuul的过滤器，可以实现对外服务的安全控制 用户名 限制〉
 *
 * @author xiangjunzhong
 * @create 2018/1/29 14:52
 * @since 1.0.0
 */
public class AccessUserNameFilter extends ZuulFilter {

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(AccessUserNameFilter.class);

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        logger.info(String.format("%s AccessUserNameFilter request to %s", request.getMethod(), request.getRequestURL().toString()));

        String username = request.getParameter("username");// 获取请求的参数
        if (null != username && username.equals("xiang")) {// 如果请求的参数不为空，且值为chhliu时，则通过
            ctx.setSendZuulResponse(true);// 对该请求进行路由
            ctx.setResponseStatusCode(200);
            ctx.set("isSuccess", true);// 设值，让下一个Filter看到上一个Filter的状态
            return null;
        } else {
            ctx.setSendZuulResponse(false);// 过滤该请求，不对其进行路由
            ctx.setResponseStatusCode(401);// 返回错误码
            ctx.setResponseBody("{\"result\":\"username is not correct!\"}");// 返回错误内容
            ctx.set("isSuccess", false);
            return null;
        }
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        // 如果前一个过滤器的结果为true，则说明上一个过滤器成功了，需要进入当前的过滤，如果前一个过滤器的结果为false，
        // 则说明上一个过滤器没有成功，则无需进行下面的过滤动作了，直接跳过后面的所有过滤器并返回结果
        return (boolean) ctx.get("isSuccess");
    }

    @Override
    public int filterOrder() {
        return 2;// 优先级为0，数字越大，优先级越低
    }

    @Override
    public String filterType() {
        return "pre";// 前置过滤器
    }
}