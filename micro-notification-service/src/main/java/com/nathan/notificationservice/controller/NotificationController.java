package com.nathan.notificationservice.controller;

import com.nathan.notificationservice.model.MessageDTO;
import com.nathan.notificationservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NotificationController {

    private final EmailService emailService;

    @PostMapping("/send-notification")
    public void sendNotification(@RequestBody MessageDTO messageDTO) {
        emailService.sendEmail(messageDTO);
    }

}
