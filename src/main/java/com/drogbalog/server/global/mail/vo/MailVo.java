package com.drogbalog.server.global.mail.vo;

import lombok.Data;

@Data
public class MailVo {
    private String toAddress;
    private String subject;
    private String body;
}
