package ca.verticaldigital.notifier.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailService {
    private String to;
    private String subject;
    private String body;
    @Autowired
    private JavaMailSender emailSender;

    public void sendBirthdayNotification(String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        emailSender.send(message);

        // Log email information to console
        System.out.println("Email sent to: " + to);
        System.out.println("Email body:\n" + body);
        }
    }

