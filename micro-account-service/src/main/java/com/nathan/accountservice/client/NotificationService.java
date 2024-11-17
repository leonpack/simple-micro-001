package com.nathan.accountservice.client;

import com.nathan.accountservice.client.fallback.NotificationServiceImpl;
import com.nathan.accountservice.model.MessageDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification-service", fallback = NotificationServiceImpl.class)
public interface NotificationService {

    @PostMapping("/send-notification")
    void sendNotification(@RequestBody MessageDTO messageDTO);

}
