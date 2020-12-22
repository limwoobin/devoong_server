package com.drogbalog.server.global.retrofit;

public class RetrofitFactory<S> {
    private final AbstractRetrofit retrofit;

    public RetrofitFactory(AbstractRetrofit retrofit) {
        this.retrofit = retrofit;
    }

    public S createService(String domain , Class<S> service) {
        return (S) retrofit.createService(domain , service);
    }
}
