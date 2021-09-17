package com.drogbalog.server.infra.retrofit;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import retrofit2.Call;

@Component
@RequiredArgsConstructor
public class GithubApiClient {
    private final RetrofitFactory<ExternalHandler> retrofitFactory;

    @Value("${github.token}")
    private String token;

    @Value("${github.uri}")
    private String baseURI;

    public String callMarkdownApi(String path) {
        ExternalHandler handler = retrofitFactory.createUnsafeService(baseURI , ExternalHandler.class);
        return new RetrofitClient<String>().execute(handler.requestGithubMarkdownApi(path, token));
    }
}
