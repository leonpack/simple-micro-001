package com.nathan.statisticservice.service;

import com.nathan.statisticservice.model.StatisticDTO;

import java.util.List;

public interface StatisticService {

    void add(StatisticDTO statisticDTO);
    List<StatisticDTO> getAll();

}
