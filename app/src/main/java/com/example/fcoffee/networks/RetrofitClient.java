package com.example.fcoffee.networks;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient(String url) {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();

        okHttpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                Request.Builder newRequest = request.newBuilder().header(
                        "Authorization",
                        "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJrb2tvbWkiLCJKV1RBdXRob3JpdGllc0tleSI6IlN0YWZmIiwiZXhwIjoxNTY5ODQzNTI2fQ.ofyMBXnpS4qFCAZY0SFaOxuqhSE0_InRLjTxzQg93OEJXMmf53icZ6t9CPpZeaqwK7NIGT7d9mKy_kW4NAByxg"
                );

                return chain.proceed(newRequest.build());

            }
        });

        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient.build())
                    .build();
        }

        return retrofit;
    }
}
