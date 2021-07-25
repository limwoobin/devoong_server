package com.drogbalog.server.infra.retrofit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.http.MediaType;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public abstract class RetrofitFactory<S> {
    private static final long DEFAULT_TIMEOUT = 30;

    protected abstract OkHttpClient.Builder createHttpClientBuilder();

    public S createService(String domain , Class<S> service) {
        OkHttpClient.Builder builder = createHttpClientBuilder();
        builder.addInterceptor(this.createInterceptor())
                .addInterceptor(this.createLoggingInterceptor())
                .connectTimeout(DEFAULT_TIMEOUT , TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT , TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT , TimeUnit.SECONDS);

        OkHttpClient client = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(domain)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit.create(service);
    }

    private Interceptor createLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        return logging;
    }

    private Interceptor createInterceptor() {
        Map<String, String> headers = this.defaultHeaders();

        return chain -> {
            Request.Builder requestBuilder = chain.request().newBuilder();
            for (String key : headers.keySet()) {
                requestBuilder.addHeader(key, headers.get(key));
            }

            return chain.proceed(requestBuilder.build());
        };
    }

    private Map<String , String> defaultHeaders() {
        final Map<String, String> defaultHeader = new HashMap<>();
        defaultHeader.put("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        defaultHeader.put("Cache-Control", "no-cache");

        return defaultHeader;
    }
}
