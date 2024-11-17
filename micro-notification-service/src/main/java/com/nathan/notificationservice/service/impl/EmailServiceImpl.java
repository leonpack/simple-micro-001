package com.nathan.notificationservice.service.impl;

import com.nathan.notificationservice.model.MessageDTO;
import com.nathan.notificationservice.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;

    @Override
    public void sendEmail(MessageDTO messageDTO) {
        try {
            logger.info("START.....Sending email");
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, StandardCharsets.UTF_8.name());

            //load template email with content
            Context context = new Context();
            context.setVariable("name", messageDTO.getToName());
            context.setVariable("content", messageDTO.getContent());
            String html = templateEngine.process("welcome-mail", context);

            //send email
            helper.setTo(messageDTO.getTo());
            helper.setText(html, true);
            helper.setSubject(messageDTO.getSubject());
            helper.setFrom(messageDTO.getFrom());
            javaMailSender.send(message);

            logger.info("END...send email");
        } catch (MessagingException e) {
            logger.error("Error occurred while sending email", e);
        }
    }
}
