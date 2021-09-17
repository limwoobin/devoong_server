package com.drogbalog.server.infra.retrofit;

import com.drogbalog.server.global.exception.DrogbalogException;
import com.drogbalog.server.global.exception.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;

@Component
@RequiredArgsConstructor
public class GithubApiClient<T> extends RetrofitClient<T , ErrorResponse> {
    private final RetrofitFactory<ExternalHandler> retrofitFactory;

    @Value("${github.token}")
    private String token;

    @Value("${github.uri}")
    private String baseURI;

    @SuppressWarnings("unchecked")
    public String callMarkdownApi(String path) {
        ExternalHandler handler = retrofitFactory.createUnsafeService(baseURI , ExternalHandler.class);
        return (String) super.execute((Call<T>) handler.requestGithubMarkdownApi(path, token));
    }

    @Override
    ErrorResponse parseErrorBody(Response<T> response) {
        HttpStatus status = HttpStatus.valueOf(response.code());
        return ErrorResponse.builder()
                .code(response.code())
                .status(status)
                .message(status.getReasonPhrase())
                .build();
    }

    @Override
    void throwException(ErrorResponse response) {
        throw new DrogbalogException(response.getStatus(), response.getCode(), response.getMessage());
    }
}
