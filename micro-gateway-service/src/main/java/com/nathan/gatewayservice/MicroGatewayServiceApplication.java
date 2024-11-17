package com.nathan.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroGatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroGatewayServiceApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder, LoggingGatewayFilterFactory loggingFactory) {
        return builder.routes()
                .route("user-route", r -> r.path("/user/**")
                        .filters(f -> f.stripPrefix(1)
                                .filter(loggingFactory.apply(new LoggingGatewayFilterFactory.Config()))
                                .circuitBreaker(cb -> cb.setName("CircuitBreaker")
                                        .getFallbackUri())).uri("lb://account-service"))
                .route("report-route", r -> r.path("/report/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://statistic-service"))
                .route("notification-route", r -> r.path("/notification/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://notification-service"))
                .build();
    }

}
