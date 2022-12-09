package com.changgou.system.filter;

import com.changgou.system.util.JwtUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取请求对象
        ServerHttpRequest request = exchange.getRequest();
        // 获取响应对象
        ServerHttpResponse response = exchange.getResponse();
        //判断当前请求是否为登录请求，如果是则直接放行
        if(request.getURI().getPath().contains("/admin/login")){
            return chain.filter(exchange);
        }
        //获取当前所有请求头信息
        HttpHeaders headers = request.getHeaders();
        //获取jwt令牌信息
        String token = headers.getFirst("token");
        //判断令牌是否存在，不存在则返回错误信息
        if(StringUtils.isEmpty(token)){
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        //解析jwt令牌，判断令牌是否合法，不合法则返回错误信息
        try{
            JwtUtil.parseJWT(token);
        }catch (Exception e){
            e.printStackTrace();
            //令牌解析失败
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        //合法放行
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
