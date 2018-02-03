/**
 * FileName: FallbackProvider
 * Author:   xiangjunzhong
 * Date:     2018/2/2 14:47
 * Description: Zuul的回退 基于故障原因的响应
 */
package com.xjz.example.spingcloudeurekazuul.fallback;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 〈一句话功能简述〉<br>
 * 〈Zuul的回退 基于故障原因的响应〉
 *
 * @author xiangjunzhong
 * @create 2018/2/2 14:47
 * @since 1.0.0
 */
@Component
public class MyFallbackProvider implements FallbackProvider {

    @Override
    public ClientHttpResponse fallbackResponse(final Throwable cause) {
        if (cause instanceof HystrixTimeoutException) {
            return response(HttpStatus.GATEWAY_TIMEOUT);
        } else {
            return fallbackResponse();
        }
    }

    @Override
    public String getRoute() {
        // 表明是为哪个微服务提供回退，*表示为所有微服务提供回退
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse() {
        return response(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ClientHttpResponse response(final HttpStatus status) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                // fallback时的状态码
                return status;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                // 数字类型的状态码，详见HttpStatus
                return status.value();
            }

            @Override
            public String getStatusText() throws IOException {
                // 状态文本，详见HttpStatus
                return status.getReasonPhrase();
            }

            @Override
            public void close() {
            }

            @Override
            public InputStream getBody() throws IOException {
                // 响应体
                return new ByteArrayInputStream("服务不可用，请稍后再试。".getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                // headers设定
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
                return headers;
            }
        };
    }
}