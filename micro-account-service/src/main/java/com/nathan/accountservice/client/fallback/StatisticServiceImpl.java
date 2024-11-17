package com.nathan.accountservice.client.fallback;

import com.nathan.accountservice.client.StatisticService;
import com.nathan.accountservice.model.StatisticDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class StatisticServiceImpl implements StatisticService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void add(StatisticDTO statisticDTO) {
        //fallback
        logger.error("Statistic service is unavailable. Falling back to default behavior.");
    }

}
