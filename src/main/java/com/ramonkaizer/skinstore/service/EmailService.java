package com.ramonkaizer.skinstore.service;

import com.ramonkaizer.skinstore.domain.entity.Pedido;
import com.ramonkaizer.skinstore.domain.entity.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String remetenteEmail;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void enviarEmailConfirmacaoPagamento(Usuario usuario, Pedido pedido) {
        log.info("Iniciando envio de e-mail assíncrono para: {}", usuario.getEmail());
        try {
            SimpleMailMessage message = getSimpleMailMessage(usuario, pedido);
            mailSender.send(message);
            log.info("E-mail de confirmação enviado com sucesso para {}", usuario.getEmail());
        } catch (MailException e) {
            log.error("Falha ao enviar e-mail de confirmação para {}: {}", usuario.getEmail(), e.getMessage(), e);
        } catch (Exception e) {
            log.error("Erro inesperado ao tentar enviar e-mail para {}: {}", usuario.getEmail(), e.getMessage(), e);
        }
    }

    private SimpleMailMessage getSimpleMailMessage(Usuario usuario, Pedido pedido) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(usuario.getEmail());
        message.setSubject("Pagamento Aprovado - Pedido #" + pedido.getId());
        message.setText(String.format(
                "Olá %s,\n\nSeu pagamento para o pedido #%d no valor de R$ %.2f foi aprovado com sucesso!\n\nObrigado por comprar na SkinStore!",
                usuario.getNome(),
                pedido.getId(),
                pedido.getValorTotal()
        ));

        message.setFrom(remetenteEmail);
        return message;
    }
}
