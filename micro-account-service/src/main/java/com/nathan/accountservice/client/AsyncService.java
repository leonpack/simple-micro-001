package com.nathan.accountservice.client;

import com.nathan.accountservice.model.MessageDTO;
import com.nathan.accountservice.model.StatisticDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AsyncService {

    @Qualifier("com.nathan.accountservice.client.NotificationService")
    private final NotificationService notificationService;
    @Qualifier("com.nathan.accountservice.client.StatisticService")
    private final StatisticService statisticService;

//    @Async
    public void sendNotification(String username, String name) {
        //send email notification
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setFrom("animallcomm@gmail.com");
        messageDTO.setTo(username);
        messageDTO.setToName(name);
        messageDTO.setSubject("New User Registration");
        messageDTO.setContent("Welcome to AnimallComm!");
        notificationService.sendNotification(messageDTO);
    }

//    @Async
    public void addStatistic(String context) {
        statisticService.add(new StatisticDTO(context, new Date()));
    }

}
