package com.fshop.gateway.filters;

import com.fshop.gateway.config.AuthProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
@RequiredArgsConstructor
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {
    private final AuthProperties authProperties;
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public int getOrder() {
        return 0;
    }
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //TODO 实现登录逻辑校验
        //1. 获取request
        ServerHttpRequest request = exchange.getRequest();
        //2. 判断是否需要检验
        RequestPath path = request.getPath();
        if(isExclude(path.toString())){
            return chain.filter(exchange);
        }
        //3. 获取请求头中的token
        String token = getToken(request);
        if(token == null){ //拦截设置状态码为401
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        //传递token
        ServerWebExchange ex = exchange.mutate()
                .request(b -> b.header("user-info",token))
                .build();
        //放行
        return chain.filter(ex);
    }
    private boolean isExclude(String antPath) {
        for (String pathPattern : authProperties.getExcludePaths()) {
            if(antPathMatcher.match(pathPattern, antPath)){
                return true;
            }
        }
        return false;
    }
    private String getToken(ServerHttpRequest request){
        String token = request.getHeaders().getFirst("authorization");
        String[] split = new String[2]; //格式Bearer oroZ-6xafW3S8SkE6tKg1WL2HwFw
        if (token != null && !token.isEmpty()) {
            split = token.split(" ");
        }else{
            return null;
        }

        return split[1];
    }
}
