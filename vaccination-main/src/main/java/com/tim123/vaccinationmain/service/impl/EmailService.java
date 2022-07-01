package com.tim123.vaccinationmain.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final Environment environment;

    @Async
    public void sendEmail(String from, String to, String subject, String body) throws MailException {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        if (from.equals("")) {
            mailMessage.setFrom(Objects.requireNonNull(environment.getProperty("spring.mail.username")));
        } else {
            mailMessage.setFrom(from);
        }
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(body);
        javaMailSender.send(mailMessage);
    }
}