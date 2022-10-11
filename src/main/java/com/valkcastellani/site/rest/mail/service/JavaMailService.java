package com.valkcastellani.site.rest.mail.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.valkcastellani.site.util.Constantes;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author Valk Castellani
 * @version 1.0
 * @date 2019-10-24
 */
@Service
public class JavaMailService implements EmailService {

    private JavaMailSender mailSender;

    private static final Logger LOGGER = LoggerFactory.getLogger(JavaMailService.class);

    @Override
    public void sendText(String from, String to, String subject, String body) throws MailException, MessagingException, IOException, URISyntaxException {
        this.sendGridMail(from, to, "", subject, body, false);
    }

    @Override
    public void sendText(String from, String to, String cc, String subject, String body) throws MailException, MessagingException, IOException, URISyntaxException {
        this.sendGridMail(from, to, cc, subject, body, false);
    }

    @Override
    public void sendHTML(String from, String to, String subject, String body) throws MailException, MessagingException, IOException, URISyntaxException {
        this.sendGridMail(from, to, "", subject, body, true);
    }

    @Override
    public void sendHTML(String from, String to, String cc, String subject, String body) throws MailException, MessagingException, IOException, URISyntaxException {
        this.sendGridMail(from, to, cc, subject, body, true);
    }

//    @Async
//    private void sendSendGridMail(String from, String to, String cc, String subject, String body, boolean bodyHTML) throws MailException, MessagingException, IOException, URISyntaxException {
//        MimeMessage mensagem = new MimeMessage(Session.getInstance(Constantes.getEmailProperties(), new Authenticator() {
//
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(Constantes.CONTA_PADRAO, Constantes.SENHA_CONTA_PADRAO);
//            }
//        }));
//        //mensagem.addFrom((Address[]) Arrays.asList(new InternetAddress(Constantes.CONTA_PADRAO), new InternetAddress(from)).toArray());
//        MimeMessageHelper helper = new MimeMessageHelper(mensagem, true, "UTF-8");
//        helper.setFrom(Constantes.EMAIL_PADRAO);
//        helper.setReplyTo(from);
//        helper.setTo(to);
//        if (!cc.isEmpty()) {
//            helper.setCc(cc);
//        }
//        helper.setSubject(subject);
//        helper.setText(body, bodyHTML);
////        if (bodyHTML) {
////            helper.addInline("topo", new ClassPathResource("cepel/images/topo-email.png"));
////            helper.addInline("facebook", new ClassPathResource("cepel/images/social/facebook.png"));
////            helper.addInline("instagram", new ClassPathResource("cepel/images/social/ig.png"));
////            helper.addInline("linkedin", new ClassPathResource("cepel/images/social/linkedin.png"));
////            helper.addInline("twitter", new ClassPathResource("cepel/images/social/twitter.png"));
////            helper.addInline("youtube", new ClassPathResource("cepel/images/social/youtube.png"));
////        }
//        mensagem.setSentDate(new Date());
//        Transport.send(mensagem, Constantes.CONTA_PADRAO, Constantes.SENHA_CONTA_PADRAO);
//    }

//    @Async
//    private void sendMail(String from, String to, String cc, String subject, String body, boolean bodyHTML) throws MailException, MessagingException, IOException, URISyntaxException {
//        MimeMessage mimeMessage = new MimeMessage(Session.getInstance(Constantes.getEmailProperties(), new Authenticator() {
//
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(Constantes.CONTA_PADRAO, Constantes.SENHA_CONTA_PADRAO);
//            }
//        }));
//        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
//        helper.setFrom(Constantes.EMAIL_PADRAO);
//        helper.setFrom(from);
//        helper.setTo(to);
//        if (!cc.isEmpty()) {
//            helper.setCc(cc);
//        }
//        helper.setSubject(subject);
//        helper.setText(body, bodyHTML);
////        helper.addInline("topo", new ClassPathResource("cepel/images/topo-email.png"));
////        helper.addInline("facebook", new ClassPathResource("cepel/images/social/facebook.png"));
////        helper.addInline("instagram", new ClassPathResource("cepel/images/social/ig.png"));
////        helper.addInline("linkedin", new ClassPathResource("cepel/images/social/linkedin.png"));
////        helper.addInline("twitter", new ClassPathResource("cepel/images/social/twitter.png"));
////        helper.addInline("youtube", new ClassPathResource("cepel/images/social/youtube.png"));
//        mailSender.send(mimeMessage);
//    }

    @Async
    private void sendGridMail(String fromS, String toS, String cc, String subject, String body, boolean bodyHTML) throws MailException, MessagingException, IOException, URISyntaxException {
        Email from = new Email(fromS);
        Email to = new Email(toS);
        Content content = new Content(bodyHTML ? "" : "text/plain", body);
        Mail mail = new Mail(from, subject, to, content);
        SendGrid sg = new SendGrid(Constantes.API_SENDGRID);
        Request request = new Request();
        Response response = new Response();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            response = sg.api(request);
        } catch (IOException ex) {
            throw ex;
        }
        if (response.getStatusCode() >= 300) {
            throw new MessagingException(response.getBody());
        }
    }
}
