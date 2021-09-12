package com.bit.controllers;

import com.bit.models.EmailMessageContent;
import com.bit.services.NotificationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationsController {

    @Autowired
    private NotificationsService notificationsService;

    @PostMapping("/send-email")
    public void sendEmail(@RequestBody EmailMessageContent emailMessageContentRequest) {

        notificationsService.sendEmail(emailMessageContentRequest);
    }
}