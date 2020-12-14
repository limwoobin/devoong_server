package com.drogbalog.server.global.retrofit;

import org.springframework.stereotype.Component;

public class RetrofitFactory<S> {
    private AbstractRetrofit retrofit;

    public RetrofitFactory(AbstractRetrofit abstractRetrofit) {
        this.retrofit = retrofit;
    }

    public S createService(String domain , Class<S> service) {
        return (S) retrofit.createService(domain , service);
    }
}
