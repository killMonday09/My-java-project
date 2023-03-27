package com.szxs.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class TokenGlobalFilter implements GlobalFilter, Ordered {
    private boolean checkToken = false;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //在此处写代码就是pre过滤器
        System.out.println("配置文件的token："+this.checkToken);

        if(this.checkToken){
            List<String> token = exchange.getRequest().getHeaders().get("token");
            //访问redis看下有没有令牌
            //此处就到redis查询

            if(null == token || "".equals(token)){
                ServerHttpResponse response = exchange.getResponse();

                //{"msg":"验证不通过}
                byte[] datas = "{\"msg\":\"验证不能通过\"}".getBytes(StandardCharsets.UTF_8);
                DataBuffer buffer = response.bufferFactory().wrap(datas);
                response.getHeaders().add("Content-Type","application/json;charset=UTF-8");
                return response.writeWith(Mono.just(buffer));
            }
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
