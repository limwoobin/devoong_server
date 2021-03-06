package com.drogbalog.server.domain.subscribe.domain.response;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "구독 데이터 응답 모델")
public class SubScribeResponse {

    private long id;
    private String email;
}
