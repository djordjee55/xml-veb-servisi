package com.tim123.vaccinationportal.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayInputStream;
import java.io.File;
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

    @Async
    public void sendEmailWithAttachment(String from, String to, String subject, String body,
                                        ByteArrayInputStream[] filesToAttach, String[] filenames) throws Exception {
        if (filesToAttach.length != filenames.length) {
            throw new Exception("Broj fajlova i njihovih imena mora biti isti");
        }
        MimeMessagePreparator preparator = mimeMessage -> {
            if (from.equals("")) {
                mimeMessage.setFrom(Objects.requireNonNull(environment.getProperty("spring.mail.username")));
            } else {
                mimeMessage.setFrom(from);
            }
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(body);

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            int idx = 0;
            for (ByteArrayInputStream inputStream: filesToAttach) {
                ByteArrayResource resource = new ByteArrayResource(IOUtils.toByteArray(inputStream));
                helper.addAttachment(filenames[idx], resource);
                idx++;
            }
            helper.setText(body);
        };
        try {
            javaMailSender.send(preparator);
        }
        catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }
}