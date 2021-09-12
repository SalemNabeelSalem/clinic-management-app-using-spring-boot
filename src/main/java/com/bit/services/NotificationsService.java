package com.bit.services;

import com.bit.models.EmailMessageContent;
import com.bit.services.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationsService {

    @Autowired
    private EmailService emailService;

    public void sendEmail(EmailMessageContent emailMessageContentInput) {

        String emailAddress = emailMessageContentInput.getEmailAddress();

        String senderName = emailMessageContentInput.getSenderName();

        String messageContent = emailMessageContentInput.getMessageContent();

        emailService.send(emailAddress, emailService.buildEmail(senderName, messageContent));
    }
}