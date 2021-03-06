package com.drogbalog.server.global.mail;

import com.drogbalog.server.global.mail.vo.MailVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class MailService {
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromAddress;

    public void sendMessage(MailVo mailVo) {
        log.info(fromAddress);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailVo.getToAddress());
        message.setFrom(fromAddress);
        message.setSubject(mailVo.getSubject());
        message.setText(mailVo.getBody());

        mailSender.send(message);
    }
}
