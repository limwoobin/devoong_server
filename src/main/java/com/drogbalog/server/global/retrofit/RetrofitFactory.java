package com.drogbalog.server.global.retrofit;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RetrofitFactory<S> {
    private final AbstractRetrofit retrofit;

    public S createService(String domain , Class<S> service) {
        return (S) retrofit.createService(domain , service);
    }
}
