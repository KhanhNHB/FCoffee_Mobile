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
                        "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJrb2tvbWkiLCJKV1RBdXRob3JpdGllc0tleSI6IlN0YWZmIiwiZXhwIjoxNTcwMDI3NzM3fQ.2uAVr2Btp5W3CF6nv37_uLsE-RYdUZns1wOJBGOB6PNkwQ2H6d54Lov7PK13jl4AbGdyZFNX3cuvMNkVP6xXrQ"
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
