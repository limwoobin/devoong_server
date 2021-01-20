package com.drogbalog.server.domain.user.domain.response;

import lombok.Data;

@Data
public class JwtResponse {
    private String accessToken;
    private String refreshToken;
}
