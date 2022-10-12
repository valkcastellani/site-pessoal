package com.valkcastellani.site.rest.mail;

import com.valkcastellani.site.rest.mail.service.EmailService;
import com.valkcastellani.site.rest.mail.service.JavaMailService;
import com.valkcastellani.site.rest.payload.MensagemDTO;
import com.valkcastellani.site.util.Constantes;
import jakarta.mail.MessagingException;
import java.io.IOException;
import java.net.URISyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


//@RestController
@Controller
@RequestMapping(value = "/mail")
public class MailController {

    private final EmailService mailService = new JavaMailService();

    private static final Logger LOGGER = LoggerFactory.getLogger(MailController.class);

    @PostMapping("/send")
    public String send(MensagemDTO mensagem) throws IOException, MailException, MessagingException, URISyntaxException {
        try {
            String mens = "DE => " + mensagem.getEmail() + Constantes.NOVALINHA;
            mens += "PARA => " + Constantes.EMAIL_PADRAO + Constantes.NOVALINHA;
            mens += "ASSUNTO => " + mensagem.getAssunto() + Constantes.NOVALINHA;
            mens += "MENSAGEM => " + Constantes.NOVALINHA;
            mens += mensagem.getMensagem();
            mailService.sendText(Constantes.CONTA_PADRAO, Constantes.CONTA_PADRAO, mensagem.getAssunto(), mens);
            return "OK";
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            return "Não foi possível enviar o sei e-mail. Tente mais tarde, ou verifique os dados informados.";
        }
    }
}
