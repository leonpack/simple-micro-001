package com.nathan.accountservice.client.config;

import feign.RequestInterceptor;
import org.springframework.cloud.openfeign.security.OAuth2AccessTokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
@SuppressWarnings("deprecation")
public class StatisticFeignClientConfiguration {

//    private final String CLIENT_REGISTRATION_ID = "statistic-service";
//
//    @Bean
//    RequestInterceptor requestInterceptor(OAuth2AuthorizedClientManager authorizedClientManager) {
//        return new OAuth2AccessTokenInterceptor(CLIENT_REGISTRATION_ID, authorizedClientManager);
//    }
}
