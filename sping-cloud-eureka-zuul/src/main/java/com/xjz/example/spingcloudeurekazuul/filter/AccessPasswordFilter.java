/**
 * FileName: AccessPasswordFilter
 * Author:   xiangjunzhong
 * Date:     2018/1/29 14:54
 * Description: 利用Zuul的过滤器，可以实现对外服务的安全控制 密码 限制
 */
package com.xjz.example.spingcloudeurekazuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * 〈一句话功能简述〉<br>
 * 〈利用Zuul的过滤器，可以实现对外服务的安全控制 密码 限制〉
 *
 * @author xiangjunzhong
 * @create 2018/1/29 14:54
 * @since 1.0.0
 */
public class AccessPasswordFilter extends ZuulFilter {

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(AccessPasswordFilter.class);

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        logger.info(String.format("%s AccessPasswordFilter request to %s", request.getMethod(), request.getRequestURL().toString()));

        String username = request.getParameter("password");
        if (null != username && username.equals("123456")) {
            ctx.setSendZuulResponse(true);
            ctx.setResponseStatusCode(200);
            ctx.set("isSuccess", true);
            return null;
        } else {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.setResponseBody("{\"result\":\"password is not correct!\"}");
            ctx.set("isSuccess", false);
            return null;
        }
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        return (boolean) ctx.get("isSuccess");
    }

    @Override
    public int filterOrder() {
        return 3; // 优先级设置为1
    }

    @Override
    public String filterType() {
        return "pre";
    }
}