package com.nathan.gatewayservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class FirstPreLastPostGlobalFilter implements GlobalFilter, Ordered {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String queryParamLocale = exchange.getRequest()
                .getQueryParams()
                .getFirst("locale");

        ServerWebExchange modifiedExchange = exchange.mutate()
                .request(request -> request.headers(httpHeaders -> httpHeaders.remove(HttpHeaders.ORIGIN)))
                .build();

        logger.info("First Pre Global Filter");
        return chain.filter(modifiedExchange)
                .then(Mono.fromRunnable(() -> {
                    logger.info("Last Post Global Filter");
                }));
    }

    @Override
    public int getOrder() {
        return -1;
    }

}