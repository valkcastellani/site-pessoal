//package com.valkcastellani.site.rest.mail;
//
//import br.cepel.dea.admsistemas.data.model.Arquivo;
//import br.cepel.dea.admsistemas.data.model.Contrato;
//import br.cepel.dea.admsistemas.data.model.Empresa;
//import br.cepel.dea.admsistemas.data.model.RegistroSenha;
//import br.cepel.dea.admsistemas.data.model.TokenConviteUsuario;
//import br.cepel.dea.admsistemas.data.model.TokenSenhaUsuario;
//import br.cepel.dea.admsistemas.data.model.Usuario;
//import br.cepel.dea.admsistemas.data.model.enumerate.TipoContato;
//import br.cepel.dea.admsistemas.data.model.enumerate.TipoEmpresa;
//import br.cepel.dea.admsistemas.data.respository.ContatoUsuarioRepository;
//import br.cepel.dea.admsistemas.data.respository.ContratoRepository;
//import br.cepel.dea.admsistemas.mail.service.JavaMailService;
//import br.cepel.dea.admsistemas.utils.Constantes;
//import br.cepel.dea.admsistemas.utils.Utils;
//import java.io.IOException;
//import java.net.URISyntaxException;
//import java.util.List;
//import java.util.Locale;
//import javax.mail.MessagingException;
//import javax.servlet.http.HttpServletRequest;
//import org.hibernate.Hibernate;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.MessageSource;
//import org.springframework.mail.MailException;
//import org.springframework.stereotype.Component;
//import org.thymeleaf.TemplateEngine;
//import org.thymeleaf.context.Context;
//
//@Component
//public class Mailer {
//
//    private JavaMailService mailService = new JavaMailService();
//
//    @Autowired
//    private ContatoUsuarioRepository contatoUsuarioRepository;
//
//    @Autowired
//    private ContratoRepository contratoRepository;
//
//    @Autowired
//    private TemplateEngine thymeleaf;
//
//    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(Mailer.class);
//
//    public void enviarEmailEsqueciMinhaSenha(Usuario usuario, TokenSenhaUsuario tokenSenhaUsuario, HttpServletRequest httpServletRequest, MessageSource messageSource, Locale locale) {
//        Context context = new Context(locale);
//        context.setVariable("usuario", usuario);
//        context.setVariable("nomeEquipe", messageSource.getMessage("equipe.WEB.DEA", null, locale));
//        context.setVariable("emailEquipe", Constantes.EMAIL_WEB_DEA);
//        adicionarVariaveisModelo(context);
//        context.setVariable("url", String.format("%s/%s", httpServletRequest.getRequestURL(), tokenSenhaUsuario.getToken()));
//        String textoEmail = thymeleaf.process("mail/mail-esqueci-minha-senha", context);
//        try {
//            mailService.sendHTML(Constantes.EMAIL_WEB_DEA, usuario.getEmail(), messageSource.getMessage("titulo.mail.esqueci.minha.senha", null, locale), textoEmail);
//            LOGGER.info("E-mail de recuperação de senha enviado para " + usuario.getEmail());
//        } catch (MessagingException | MailException | IOException | URISyntaxException ex) {
//            escreveLogErroEnvioEmail(Constantes.EMAIL_WEB_DEA, usuario.getEmail(), "", messageSource.getMessage("titulo.mail.esqueci.minha.senha", null, locale), textoEmail, ex.getMessage(), ex.getStackTrace().toString());
//            throw new RuntimeException(ex.getMessage());
//        }
//    }
//
//    public void enviarEmailSenhaAlterada(Usuario usuario, HttpServletRequest request, MessageSource messageSource, Locale locale) {
//        Context context = new Context(locale);
//        context.setVariable("usuario", usuario);
//        context.setVariable("nomeEquipe", messageSource.getMessage("equipe.WEB.DEA", null, locale));
//        context.setVariable("emailEquipe", Constantes.EMAIL_WEB_DEA);
//        context.setVariable("url", String.format("%s", request.getRequestURL()));
//        context.setVariable("ip", String.format("IP: %s / PROXY: %s", Utils.getIpAddress(request), request.getRemoteHost()));
//        context.setVariable("info", Utils.getInformacaoIP(Utils.getIpAddress(request), messageSource, locale));
//        adicionarVariaveisModelo(context);
//        String textoEmail = thymeleaf.process("mail/mail-senha-alterada", context);
//        try {
//            mailService.sendHTML(Constantes.EMAIL_WEB_DEA, usuario.getEmail(), messageSource.getMessage("titulo.mail.senha.alterada", null, locale), textoEmail);
//            LOGGER.info("E-mail de aviso de senha alterada foi enviado para " + usuario.getEmail());
//        } catch (MessagingException | MailException | IOException | URISyntaxException ex) {
//            escreveLogErroEnvioEmail(Constantes.EMAIL_WEB_DEA, usuario.getEmail(), "", messageSource.getMessage("titulo.senha.alterada", null, locale), textoEmail, ex.getMessage(), ex.getStackTrace().toString());
//            throw new RuntimeException(ex.getMessage());
//        }
//    }
//
//    public void enviarEmailUsuarioCadastradoComSenha(Usuario usuario, String senha, HttpServletRequest httpServletRequest, MessageSource messageSource, Locale locale) {
//        Context context = new Context(locale);
//        context.setVariable("usuario", usuario);
//        context.setVariable("senha", senha);
//        adicionarVariaveisModelo(context);
//        String textoEmail = thymeleaf.process("mail/mail-usuario-cadastrado", context);
//        try {
//            mailService.sendHTML(Constantes.EMAIL_WEB_DEA, usuario.getEmail(), messageSource.getMessage("titulo.bem.vindo.webdea.cadastro.concluido", null, locale), textoEmail);
//            LOGGER.info("E-mail de aviso de usuário cadastrado foi enviado para " + usuario.getEmail());
//        } catch (MessagingException | MailException | IOException | URISyntaxException ex) {
//            escreveLogErroEnvioEmail(Constantes.EMAIL_WEB_DEA, usuario.getEmail(), "", messageSource.getMessage("titulo.bem.vindo.webdea.cadastro.concluido", null, locale), textoEmail, ex.getMessage(), ex.getStackTrace().toString());
//            throw new RuntimeException(ex.getMessage());
//        }
//    }
//
//    public void enviarEmailConviteUsuario(TokenConviteUsuario tokenConviteUsuario, HttpServletRequest request, MessageSource messageSource, Locale locale) {
//        Context context = new Context(locale);
//        adicionarVariaveisModelo(context);
//        context.setVariable("nomeEquipe", messageSource.getMessage("equipe.WEB.DEA", null, locale));
//        context.setVariable("emailEquipe", Constantes.EMAIL_WEB_DEA);
//        context.setVariable("empresa", tokenConviteUsuario.getEmpresa());
//        context.setVariable("perfil", tokenConviteUsuario.getPerfil());
//        context.setVariable("url", String.format("%s/%s", request.getRequestURL().toString().replaceAll("/usuario", ""), tokenConviteUsuario.getToken()));
//        String textoEmail = thymeleaf.process("mail/mail-convite-usuario", context);
//        try {
//            mailService.sendHTML(Constantes.EMAIL_WEB_DEA, tokenConviteUsuario.getEmail(), messageSource.getMessage("titulo.bem.vindo.webdea", null, locale), textoEmail);
//            LOGGER.info("E-mail de convite de usuário foi enviado para " + tokenConviteUsuario.getEmail());
//        } catch (MessagingException | MailException | IOException | URISyntaxException ex) {
//            escreveLogErroEnvioEmail(Constantes.EMAIL_WEB_DEA, tokenConviteUsuario.getEmail(), "", messageSource.getMessage("titulo.bem.vindo.webdea", null, locale), textoEmail, ex.getMessage(), ex.getStackTrace().toString());
//            throw new RuntimeException(ex.getMessage());
//        }
//    }
//
//    public void enviarEmailResetarSenhaUsuario(Usuario usuario, String senhaGerada, HttpServletRequest httpServletRequest, MessageSource messageSource, Locale locale) {
//        Context context = new Context(locale);
//        context.setVariable("usuario", usuario);
//        context.setVariable("senha", senhaGerada);
//        context.setVariable("nomeEquipe", messageSource.getMessage("equipe.WEB.DEA", null, locale));
//        context.setVariable("emailEquipe", Constantes.EMAIL_WEB_DEA);
//        adicionarVariaveisModelo(context);
//        String textoEmail = thymeleaf.process("mail/mail-senha-usuario-resetada", context);
//        try {
//            mailService.sendHTML(Constantes.EMAIL_WEB_DEA, usuario.getEmail(), messageSource.getMessage("titulo.mail.redefinicao.senha", null, locale), textoEmail);
//            LOGGER.info("E-mail de aviso de senha do usuário resetada foi enviado para " + usuario.getEmail());
//        } catch (MessagingException | MailException | IOException | URISyntaxException ex) {
//            escreveLogErroEnvioEmail(Constantes.EMAIL_WEB_DEA, usuario.getEmail(), "", messageSource.getMessage("titulo.mail.redefinicao.senha", null, locale), textoEmail, ex.getMessage(), ex.getStackTrace().toString());
//            throw new RuntimeException(ex.getMessage());
//        }
//    }
//
//    public void enviarEmailPublicacaoVersao(Usuario usuario, Arquivo arquivo, Contrato contrato, Empresa empresa, MessageSource messageSource, Locale locale) {
//        Hibernate.initialize(arquivo.getPlataforma());
//        Hibernate.initialize(arquivo.getProgramaVersao());
//        Hibernate.initialize(arquivo.getProgramaVersao().getPrograma());
//        Context context = new Context(locale);
//        String tituloEmail = messageSource.getMessage("titulo.mail.publicacao", null, locale);
//        String nomeEquipe = messageSource.getMessage("equipe.WEB.DEA", null, locale);
//        String texto = messageSource.getMessage("texto.mail.arquivo.publicado", null, locale);
//        String duvida = messageSource.getMessage("mail.texto.duvida", null, locale);
//        String emailEquipe = arquivo.getPrograma().getEmail().trim();
//        tituloEmail = tituloEmail.replace("<modelo-versao>", arquivo.getPrograma().getNome().trim() + " " + arquivo.getProgramaVersao().getVersao().trim());
//        nomeEquipe = nomeEquipe.replace("WEB-DEA", arquivo.getPrograma().getNome().trim());
//        nomeEquipe = nomeEquipe.replace("WEB DEA", arquivo.getPrograma().getNome().trim());
//        texto = texto.replace("<nome-do-modelo>", arquivo.getPrograma().getNome().trim());
//        duvida = duvida.replace("<email-modelo>", emailEquipe);
//        context.setVariable("usuario", usuario);
//        context.setVariable("arquivo", arquivo);
//        context.setVariable("contrato", contrato);
//        context.setVariable("empresa", empresa);
//        context.setVariable("tituloEmail", tituloEmail);
//        context.setVariable("nomeEquipe", nomeEquipe);
//        context.setVariable("emailEquipe", emailEquipe);
//        context.setVariable("texto", texto);
//        context.setVariable("duvida", duvida);
//        adicionarVariaveisModelo(context);
//        String textoEmail = thymeleaf.process("mail/mail-publicacao-arquivo", context);
//        try {
//            mailService.sendHTML(emailEquipe, usuario.getEmail(), tituloEmail, textoEmail);
//            LOGGER.info("E-mail de aviso de publicação de arquivo foi enviado para " + usuario.getEmail());
//        } catch (MessagingException | MailException | IOException | URISyntaxException ex) {
//            escreveLogErroEnvioEmail(emailEquipe, usuario.getEmail(), "", tituloEmail, textoEmail, ex.getMessage(), ex.getStackTrace().toString());
//            throw new RuntimeException(ex.getMessage());
//        }
//    }
//
//    public void enviarEmailArquivoPublicadoListaEmpresas(Usuario usuario, Arquivo arquivo, List<Empresa> empresas, MessageSource messageSource, Locale locale) {
//        Hibernate.initialize(arquivo.getPlataforma());
//        Hibernate.initialize(arquivo.getProgramaVersao());
//        Hibernate.initialize(arquivo.getProgramaVersao().getPrograma());
//        arquivo.setPrograma(arquivo.getProgramaVersao().getPrograma());
//        Context context = new Context(locale);
//        String tituloEmail = messageSource.getMessage("titulo.mail.publicacao", null, locale);
//        String nomeEquipe = messageSource.getMessage("equipe.WEB.DEA", null, locale);
//        String texto = messageSource.getMessage("texto.mail.eu.publiquei.arquivo", null, locale);
//        String duvida = messageSource.getMessage("mail.texto.duvida", null, locale);
//        String emailEquipe = arquivo.getPrograma().getEmail().trim();
//        tituloEmail = tituloEmail.replace("<modelo-versao>", arquivo.getPrograma().getNome().trim() + " " + arquivo.getProgramaVersao().getVersao().trim());
//        nomeEquipe = nomeEquipe.replace("WEB-DEA", arquivo.getPrograma().getNome().trim());
//        nomeEquipe = nomeEquipe.replace("WEB DEA", arquivo.getPrograma().getNome().trim());
//        texto = texto.replace("<nome-arquivo>", arquivo.getNome().trim());
//        duvida = duvida.replace("<email-modelo>", emailEquipe);
//        context.setVariable("usuario", usuario);
//        context.setVariable("arquivo", arquivo);
//        context.setVariable("empresas", empresas);
//        context.setVariable("tituloEmail", tituloEmail);
//        context.setVariable("nomeEquipe", nomeEquipe);
//        context.setVariable("emailEquipe", emailEquipe);
//        context.setVariable("texto", texto);
//        context.setVariable("duvida", duvida);
//        adicionarVariaveisModelo(context);
//        String textoEmail = thymeleaf.process("mail/mail-publicacao-arquivo-equipe", context);
//        try {
//            mailService.sendHTML(Constantes.EMAIL_WEB_DEA, emailEquipe, tituloEmail, textoEmail);
//            LOGGER.info("E-mail de aviso de publicação de arquivo foi enviado para " + emailEquipe);
//        } catch (MessagingException | MailException | IOException | URISyntaxException ex) {
//            escreveLogErroEnvioEmail(emailEquipe, emailEquipe, "", tituloEmail, textoEmail, ex.getMessage(), ex.getStackTrace().toString());
//            throw new RuntimeException(ex.getMessage());
//        }
//    }
//
//    public void enviarEmailContratoAExpirar(Contrato contrato, int numDias, MessageSource messageSource) {
//        Locale locale = new Locale("pt", "BR");
//        if (contrato.getEmpresa().getTipoEmpresa().equals(TipoEmpresa.ESTRANGEIRA)) {
//            locale = new Locale("en", "US");
//        }
//        Context context = new Context(locale);
//        String textoEmail = "";
//        String titulo = messageSource.getMessage("titulo.mail.expiracao.contrato", null, locale).replace("<nome-modelo>", contrato.getPrograma().getNome());
//        context.setVariable("usuario", contrato.getUsuario());
//        context.setVariable("mensagemVermelho", messageSource.getMessage("mail.texto.falta.dias.exprirar.contrato.modelo", null, locale).replace("<numero-dias>", String.valueOf(numDias)).replace("<nome-modelo>", contrato.getPrograma().getNome()));
//        context.setVariable("programa", contrato.getPrograma());
//        context.setVariable("empresa", contrato.getEmpresa());
//        adicionarVariaveisModelo(context);
//        switch (contrato.getTipoContrato().getNome().trim().toLowerCase()) {
//            case "gratuito":
//            case "gratuíto":
//                context.setVariable("nomeEquipe", messageSource.getMessage("label.dea.contratos", null, locale));
//                context.setVariable("emailEquipe", Constantes.EMAIL_CONTRATO_DEA);
//                textoEmail = thymeleaf.process("mail/mail-contrato-gratuito-a-expirar", context);
//                break;
//            case "academico":
//            case "acadêmico":
//                context.setVariable("nomeEquipe", messageSource.getMessage("label.dea.academico", null, locale));
//                context.setVariable("emailEquipe", Constantes.EMAIL_ACADEMICO_DEA);
//                textoEmail = thymeleaf.process("mail/mail-contrato-academica-a-expirar", context);
//                break;
//            default:
//                context.setVariable("nomeEquipe", messageSource.getMessage("label.dea.contratos", null, locale));
//                context.setVariable("emailEquipe", Constantes.EMAIL_CONTRATO_DEA);
//                textoEmail = thymeleaf.process("mail/mail-contrato-pago-a-expirar", context);
//                break;
//        }
//        try {
//            mailService.sendHTML(context.getVariable("emailEquipe").toString(), contrato.getUsuario().getEmail(), titulo, textoEmail);
//            LOGGER.info("E-mail de expiração de contrato enviado para " + contrato.getUsuario().getEmail());
//        } catch (MessagingException | MailException | IOException | URISyntaxException ex) {
//            escreveLogErroEnvioEmail(context.getVariable("emailEquipe").toString(), contrato.getUsuario().getEmail(), "", titulo, textoEmail, ex.getMessage(), ex.getStackTrace().toString());
//            throw new RuntimeException(ex.getMessage());
//        }
//    }
//
//    public void enviarEmailDownloadLicenca(Usuario usuario, Long idContrato, MessageSource messageSource, HttpServletRequest request, Locale locale) {
//        Contrato contrato = contratoRepository.buscarComTransientes(idContrato);
//        enviarEmailDownloadLicenca(usuario, contrato.getEmpresa(), contrato, messageSource, request, locale);
//    }
//
//    public void enviarEmailDownloadLicenca(Usuario usuario, Empresa empresa, Contrato contrato, MessageSource messageSource, HttpServletRequest request, Locale locale) {
//        String tituloEmail = messageSource.getMessage("titulo.mail.download.licenca", null, locale).replace("<nome-modelo>", contrato.getPrograma().getNome());
//        Context context = new Context(locale);
//        context.setVariable("usuario", usuario);
//        context.setVariable("empresa", empresa);
//        context.setVariable("contrato", contrato);
//        context.setVariable("nomeEquipe", messageSource.getMessage("equipe.WEB.DEA", null, locale));
//        context.setVariable("textoLicenca", messageSource.getMessage("mail.texto.download.arquivo.licenca", null, locale).replace("<nome-modelo>", contrato.getPrograma().getNome()));
//        context.setVariable("emailEquipe", Constantes.EMAIL_WEB_DEA);
//        context.setVariable("ip", String.format("IP: %s / PROXY: %s", Utils.getIpAddress(request), request.getRemoteHost()));
//        context.setVariable("info", Utils.getInformacaoIP(Utils.getIpAddress(request), messageSource, locale));
//        adicionarVariaveisModelo(context);
//        String textoEmail = thymeleaf.process("mail/mail-download-licenca", context);
//        try {
//            mailService.sendHTML(Constantes.EMAIL_WEB_DEA, contrato.getPrograma().getEmail(), Constantes.EMAIL_CONTRATO_DEA, tituloEmail, textoEmail);
//            LOGGER.info("E-mail de aviso de download de arquivo de licença foi enviado para " + contrato.getPrograma().getEmail());
//        } catch (MessagingException | MailException | IOException | URISyntaxException ex) {
//            escreveLogErroEnvioEmail(Constantes.EMAIL_WEB_DEA, contrato.getPrograma().getEmail(), Constantes.EMAIL_CONTRATO_DEA, tituloEmail, textoEmail, ex.getMessage(), ex.getStackTrace().toString());
//            throw new RuntimeException(ex.getMessage());
//        }
//    }
//
//    public void enviarEmailPublicacaoAutomaticaListaArquivos(Empresa empresa, List<Arquivo> arquivos, MessageSource messageSource, Locale locale) {
//        arquivos.forEach(arquivo -> {
//            Hibernate.initialize(arquivo.getPlataforma());
//            Hibernate.initialize(arquivo.getProgramaVersao());
//            Hibernate.initialize(arquivo.getProgramaVersao().getPrograma());
//            arquivo.setPrograma(arquivo.getProgramaVersao().getPrograma());
//        });
//        Context context = new Context(locale);
//        String tituloEmail = messageSource.getMessage("titulo.mail.publicacao.automatica", null, locale);
//        String equipe = messageSource.getMessage("equipe.WEB.DEA", null, locale);
//        equipe = equipe.replace("WEB-DEA", arquivos.get(0).getPrograma().getNome().trim());
//        equipe = equipe.replace("WEB DEA", arquivos.get(0).getPrograma().getNome().trim());
//        String email = arquivos.get(0).getPrograma().getEmail();
//        String nomeEquipe = messageSource.getMessage("equipe.WEB.DEA", null, locale);
//        String emailEquipe = Constantes.EMAIL_WEB_DEA;
//        String texto = messageSource.getMessage("texto.mail.publicacao.automatica", null, locale);
//        String duvida = messageSource.getMessage("mail.texto.duvida", null, locale);
//        texto = texto.replace("<nome-empresa>", empresa.getNomeFantasia().trim());
//        texto = texto.replace("<nome-modelo>", arquivos.get(0).getPrograma().getNome().trim());
//        duvida = duvida.replace("<email-modelo>", emailEquipe);
//        context.setVariable("equipe", equipe);
//        context.setVariable("modelo", arquivos.get(0).getPrograma().getNome().trim());
//        context.setVariable("arquivos", arquivos);
//        context.setVariable("empresa", empresa);
//        context.setVariable("tituloEmail", tituloEmail);
//        context.setVariable("nomeEquipe", nomeEquipe);
//        context.setVariable("emailEquipe", emailEquipe);
//        context.setVariable("texto", texto);
//        context.setVariable("duvida", duvida);
//        adicionarVariaveisModelo(context);
//        String textoEmail = thymeleaf.process("mail/mail-publicacao-arquivo-equipe", context);
//        try {
//            mailService.sendHTML(email, emailEquipe, tituloEmail, textoEmail);
//            LOGGER.info("E-mail de aviso de publicação de arquivo foi enviado para " + emailEquipe);
//        } catch (MessagingException | MailException | IOException | URISyntaxException ex) {
//            escreveLogErroEnvioEmail(email, emailEquipe, "", tituloEmail, textoEmail, ex.getMessage(), ex.getStackTrace().toString());
//            throw new RuntimeException(ex.getMessage());
//        }
//    }
//
//    public void enviarEmailTrocaUsuarioExterno(Empresa empresa, Usuario usuario, Usuario usuarioAntigo, HttpServletRequest httpServletRequest, MessageSource messageSource, Locale locale) {
//        Context context = new Context(locale);
//        context.setVariable("usuario", usuario);
//        context.setVariable("usuarioAntigo", usuarioAntigo);
//        context.setVariable("nomeEquipe", messageSource.getMessage("equipe.WEB.DEA", null, locale));
//        context.setVariable("emailEquipe", Constantes.EMAIL_WEB_DEA);
//        String mensagem = messageSource.getMessage("mail.texto.troca.usuario.externo", null, locale);
//        context.setVariable("mensagem", mensagem.replace("[EMPRESA]", empresa.getNomeFantasia()));
//        adicionarVariaveisModelo(context);
//        String textoEmail = thymeleaf.process("mail/mail-troca-usuario-externo", context);
//        try {
//            mailService.sendHTML(Constantes.EMAIL_WEB_DEA, usuario.getEmail(), Constantes.EMAIL_WEB_DEA, messageSource.getMessage("titulo.mail.troca.usuario.externo", null, locale), textoEmail);
//            LOGGER.info("E-mail de alteração de usuário externo enviado para " + usuario.getEmail());
//        } catch (MessagingException | MailException | IOException | URISyntaxException ex) {
//            escreveLogErroEnvioEmail(Constantes.EMAIL_WEB_DEA, usuario.getEmail(), "", messageSource.getMessage("titulo.mail.troca.usuario.externo", null, locale), textoEmail, ex.getMessage(), ex.getStackTrace().toString());
//            throw new RuntimeException(ex.getMessage());
//        }
//    }
//
//    public void enviarEmailRegistroSenha(RegistroSenha registroSenha, MessageSource messageSource, Locale locale) {
//        Hibernate.initialize(registroSenha.getPrograma());
//        Hibernate.initialize(registroSenha.getUsuario());
//        String emailUsuario = contatoUsuarioRepository.findFirstByUsuarioAndTipoContato(registroSenha.getUsuario(), TipoContato.EMAIL).getContato();
//        Context context = new Context(locale);
//        String tituloEmail = messageSource.getMessage("titulo.mail.registro.senha", null, locale);
//        String nomeEquipe = messageSource.getMessage("equipe.WEB.DEA", null, locale);
//        String emailEquipe = Constantes.EMAIL_ENCAD;
//        tituloEmail = tituloEmail.replace("<modelo-versao>", registroSenha.getPrograma().getNome().trim());
//        context.setVariable("usuario", registroSenha.getUsuario());
//        context.setVariable("registro", registroSenha);
//        context.setVariable("tituloEmail", tituloEmail);
//        context.setVariable("nomeEquipe", nomeEquipe);
//        context.setVariable("emailEquipe", emailEquipe);
//        adicionarVariaveisModelo(context);
//        String textoEmail = thymeleaf.process("mail/mail-registro-senha", context);
//        try {
//            mailService.sendHTML(emailEquipe, emailUsuario, tituloEmail, textoEmail);
//            LOGGER.info("E-mail com registro e senha foi enviado para " + emailUsuario);
//        } catch (MessagingException | MailException | IOException | URISyntaxException ex) {
//            escreveLogErroEnvioEmail(emailEquipe, emailUsuario, "", tituloEmail, textoEmail, ex.getMessage(), ex.getStackTrace().toString());
//            throw new RuntimeException(ex.getMessage());
//        }
//    }
//
//    public void enviarEmailMensagemErro(String titulo, String mensagem) {
//        try {
//            mailService.sendText(Constantes.EMAIL_WEB_DEA, Constantes.EMAIL_ENCAD, titulo, mensagem);
//            LOGGER.info("E-mail com registro e senha foi enviado para " + Constantes.EMAIL_ENCAD);
//        } catch (MessagingException | MailException | IOException | URISyntaxException ex) {
//            escreveLogErroEnvioEmail(Constantes.EMAIL_WEB_DEA, Constantes.EMAIL_ENCAD, "", titulo, mensagem, ex.getMessage(), ex.getStackTrace().toString());
//            throw new RuntimeException(ex.getMessage());
//        }
//    }
//
//    private void adicionarVariaveisModelo(Context context) {
//        context.setVariable("topo", "topo");
//        context.setVariable("facebook", "facebook");
//        context.setVariable("instagram", "instagram");
//        context.setVariable("linkedin", "linkedin");
//        context.setVariable("twitter", "twitter");
//        context.setVariable("youtube", "youtube");
//    }
//
//    private void escreveLogErroEnvioEmail(String DE, String PARA, String CC, String TITULO, String MENSAGEM, String ERRO, String STACK) {
//        LOGGER.error("**************************************************************************************");
//        LOGGER.error("* ERRO: Ao tentar enviar e-mail                                                      *");
//        LOGGER.error("**************************************************************************************");
//        LOGGER.error("* DE               : " + DE);
//        LOGGER.error("* PARA             : " + PARA);
//        LOGGER.error("* CC               : " + CC);
//        LOGGER.error("* ASSUNTO          : " + TITULO);
//        LOGGER.error("* MENSAGEM         : " + MENSAGEM);
//        LOGGER.error("* Mensagem de Erro : " + ERRO);
//        LOGGER.error("* StackTrace       : " + STACK);
//        LOGGER.error("**************************************************************************************");
//    }
//}
