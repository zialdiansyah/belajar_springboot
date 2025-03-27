package com.zialdiansyah.spring.service;

import org.springframework.stereotype.Service;

import jakarta.mail.*;
import jakarta.mail.internet.*;

@Service
public class EmailService {
    private final Session session;

    public EmailService(Session session) {
        this.session = session;
    }

    public void sendMail(String destinationAddress, String mailSubject, String messageContent) throws Exception {

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("from@gmail.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinationAddress));
        message.setSubject(mailSubject);
        String msg = messageContent;
        
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html; charset=utf-8");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);

        System.out.println("Mail sent");
    }
}
