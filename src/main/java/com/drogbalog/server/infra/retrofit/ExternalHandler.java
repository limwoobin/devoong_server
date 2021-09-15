package com.drogbalog.server.infra.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Url;

public interface ExternalHandler {

    @GET
    Call<String> requestGithubMarkdownApi(@Url String uri , @Header("Authorization") String token);
}
