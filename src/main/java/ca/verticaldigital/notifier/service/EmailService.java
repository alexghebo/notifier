package ca.verticaldigital.notifier.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private String recipient;
    private String subject;
    private String body;
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendBirthdayNotification(String recipient, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipient);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
        System.out.println("Email sent to " + recipient + " with body: " + body);


        // Log email information to console
        System.out.println("Email sent to: " + recipient);
        System.out.println("Email body:\n" + body);
        }
    }

