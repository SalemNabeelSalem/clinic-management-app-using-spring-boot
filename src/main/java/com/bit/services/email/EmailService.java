package com.bit.services.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void send(String to, String email) {

        try {

            MimeMessage mimeMessage = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

            helper.setText(email, true);

            helper.setTo(to);

            helper.setSubject("Your Appointment With Doctor.");

            helper.setFrom("hello@salem.com");

            mailSender.send(mimeMessage);

        } catch (MessagingException e) {

            LOGGER.error("failed to send email.", e);

            throw new IllegalStateException("failed to send email.");
        }
    }

    public String buildEmail(String name, String message) {

        return "<h1 style='text-align:center'>Hello Mr: " + name + "</h1>" +
                "<p style='text-align:center'>" + message + "</p>";
    }
}
