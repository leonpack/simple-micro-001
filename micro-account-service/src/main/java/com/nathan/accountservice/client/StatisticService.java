package com.nathan.accountservice.client;

import com.nathan.accountservice.client.config.StatisticFeignClientConfiguration;
import com.nathan.accountservice.client.fallback.StatisticServiceImpl;
import com.nathan.accountservice.model.StatisticDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "statistic-service", fallback = StatisticServiceImpl.class, configuration = StatisticFeignClientConfiguration.class)
public interface StatisticService {

    @PostMapping("/statistics")
    void add(@RequestBody StatisticDTO statisticDTO);

}
