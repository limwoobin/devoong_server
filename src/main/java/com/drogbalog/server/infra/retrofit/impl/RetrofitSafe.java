package com.drogbalog.server.infra.retrofit.impl;

import com.drogbalog.server.infra.retrofit.RetrofitFactory;
import okhttp3.OkHttpClient;

public class RetrofitSafe<S> extends RetrofitFactory<S> {

    @Override
    public S createService(String domain, Class<S> service) {
        return super.createService(domain, service);
    }

    @Override
    protected OkHttpClient.Builder createHttpClientBuilder() {
        return new OkHttpClient.Builder();
    }
}
