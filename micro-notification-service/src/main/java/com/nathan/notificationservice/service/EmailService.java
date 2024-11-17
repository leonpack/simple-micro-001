package com.nathan.notificationservice.service;

import com.nathan.notificationservice.model.MessageDTO;

public interface EmailService {
    void sendEmail(MessageDTO messageDTO);
}
