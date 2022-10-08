/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.valkcastellani.site.util;

import java.io.Serializable;
import java.util.Properties;

/**
 * @author Valk Castellani
 * @version 1.0
 * @date 2021-09-11
 */
public class Constantes implements Serializable {

    public static final String SERVIDOR_SMTP = "smtp.sendgrid.net";
    public static final int PORTA_SERVIDOR_SMTP = 25;
    public static final String EMAIL_PADRAO = "contato@valkcastellani.com.br";
    public static final String API_SENDGRID = "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";
    public static final String CONTA_PADRAO = "valkcastellani@gmail.com";
    public static final String SENHA_CONTA_PADRAO = "K@roL2006";
    public static final String NOVALINHA = System.getProperty("line.separator");

    public static final Properties getEmailProperties() {
        final Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", true);
	properties.put("mail.smtp.debug", true);
        properties.put("mail.smtp.host", SERVIDOR_SMTP);
        properties.put("mail.smtp.port", PORTA_SERVIDOR_SMTP);
        properties.put("mail.smtp.starttls.required", false);
        properties.put("mail.smtp.starttls.enable", false);
        properties.put("mail.smtp.ssl.enable", false);
        properties.put("mail.smtp.connectiontimeout", 10000); // miliseconds
        properties.put("mail.debug", true);

        return properties;
    }
}
