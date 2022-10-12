package com.valkcastellani.site.rest.mail.service;

import jakarta.mail.MessagingException;
import java.io.IOException;
import java.net.URISyntaxException;
import org.springframework.mail.MailException;

/**
 * @author Valk Castellani
 * @version 1.0
 * @date 2021-09-11
 */
public interface EmailService {

    public void sendText(String from, String to, String subject, String body) throws MailException, MessagingException, IOException, URISyntaxException;

    public void sendText(String from, String to, String cc, String subject, String body) throws MailException, MessagingException, IOException, URISyntaxException;

    public void sendHTML(String from, String to, String subject, String body) throws MailException, MessagingException, IOException, URISyntaxException;

    public void sendHTML(String from, String to, String cc, String subject, String body) throws MailException, MessagingException, IOException, URISyntaxException;
}
