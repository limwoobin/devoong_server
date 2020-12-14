package com.drogbalog.server.global.retrofit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class RetrofitFactory<S> {
    private static final long DEFAULT_TIMEOUT = 30;


    public S createService(String domain , Class<S> service) {
        return this.createService(domain , service , null , null , null);
    }

    public S createService(String domain, Class<S> service, Duration connectTimeout, Duration readTimeout, Duration writeTimeout) {
        Retrofit.Builder retrofit = this.createRetrofit(null , domain , connectTimeout , readTimeout , writeTimeout);
        Retrofit retrofitClient = retrofit.build();

        return retrofitClient.create(service);
    }

    public S createUnsafeService(String domain, Class<S> service) {
        return this.createUnsafeService(domain, service , null , null , null);
    }

    public S createUnsafeService(String domain, Class<S> service , Duration connectTimeout, Duration readTimeout, Duration writeTimeout) {
        Retrofit.Builder retrofit = this.createRetrofit(this.getUnsafeOkHttpClient() , domain , connectTimeout , readTimeout , writeTimeout);
        Retrofit retrofitClient = retrofit.build();

        return retrofitClient.create(service);
    }

    private Retrofit.Builder createRetrofit(OkHttpClient.Builder safeType , String domain , Duration connectTimeout, Duration readTimeout, Duration writeTimeout) {
        OkHttpClient.Builder builder = getOkHttpType(safeType);

        builder.addInterceptor(this.createInterceptor())
                .addInterceptor(this.createLoggingInterceptor());
        builder = this.setTimeout(builder , connectTimeout , readTimeout , writeTimeout);

        OkHttpClient client = builder.build();

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(domain)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client);

        return retrofitBuilder;
    }

    private OkHttpClient.Builder getOkHttpType(OkHttpClient.Builder safeType) {
        if (StringUtils.isEmpty(safeType)) {
            return new OkHttpClient.Builder();
        } else {
            return safeType;
        }
    }

    private OkHttpClient.Builder getUnsafeOkHttpClient() {
        try {
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                    .sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0])
                    .hostnameVerifier((hostname, session) -> true);
            return builder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

    private OkHttpClient.Builder setTimeout(OkHttpClient.Builder builder , Duration connectTimeout, Duration readTimeout, Duration writeTimeout) {
        if (!StringUtils.isEmpty(connectTimeout)) {
            builder.connectTimeout(connectTimeout);
        } else {
            builder.connectTimeout(DEFAULT_TIMEOUT , TimeUnit.SECONDS);
        }

        if (!StringUtils.isEmpty(readTimeout)) {
            builder.readTimeout(readTimeout);
        } else {
            builder.readTimeout(DEFAULT_TIMEOUT , TimeUnit.SECONDS);
        }

        if (!StringUtils.isEmpty(writeTimeout)) {
            builder.writeTimeout(writeTimeout);
        } else {
            builder.writeTimeout(DEFAULT_TIMEOUT , TimeUnit.SECONDS);
        }

        return builder;
    }

    private Interceptor createLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }

    private Map<String , String> defaultHeaders() {
        Map<String, String> defaultHeader = new HashMap<>();
        defaultHeader.put("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        defaultHeader.put("Cache-Control", "no-cache");
        return defaultHeader;
    }
}
