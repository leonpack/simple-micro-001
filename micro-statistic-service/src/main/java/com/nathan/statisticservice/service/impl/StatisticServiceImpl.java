package com.nathan.statisticservice.service.impl;

import com.nathan.statisticservice.entity.Statistic;
import com.nathan.statisticservice.model.StatisticDTO;
import com.nathan.statisticservice.repository.StatisticRepository;
import com.nathan.statisticservice.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {

    private final StatisticRepository statisticRepository;
    private final ModelMapper modelMapper;

    @Override
    public void add(StatisticDTO statisticDTO) {
        Statistic statistic = modelMapper.map(statisticDTO, Statistic.class);
        statisticRepository.save(statistic);
    }

    @Override
    public List<StatisticDTO> getAll() {
        List<StatisticDTO> statisticList = new ArrayList<>();
        statisticRepository.findAll().stream().map(statistic -> modelMapper.map(statistic, StatisticDTO.class)).forEach(statisticList::add);
        return statisticList;
    }
}
