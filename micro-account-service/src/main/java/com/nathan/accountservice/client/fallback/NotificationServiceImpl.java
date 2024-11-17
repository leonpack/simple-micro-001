package com.nathan.accountservice.client.fallback;

import com.nathan.accountservice.client.NotificationService;
import com.nathan.accountservice.model.MessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class NotificationServiceImpl implements NotificationService {

    Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);

    @Override
    public void sendNotification(MessageDTO messageDTO) {
        logger.error("Error sending notification: {}", messageDTO);
    }

}
