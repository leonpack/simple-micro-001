package com.nathan.statisticservice.controller;

import com.nathan.statisticservice.model.StatisticDTO;
import com.nathan.statisticservice.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final StatisticService statisticService;

    @PostMapping
    public StatisticDTO add(@RequestBody StatisticDTO statisticDTO) {
        logger.info("Add statistic: {}", statisticDTO);
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        statisticService.add(statisticDTO);
        return statisticDTO;
    }

    @GetMapping
    public List<StatisticDTO> getAll() {
        logger.info("Get all statistics");
        return statisticService.getAll();
    }

}
