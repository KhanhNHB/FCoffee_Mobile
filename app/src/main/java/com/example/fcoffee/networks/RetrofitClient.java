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
                        "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJraGFuaG5oYiIsIkpXVEF1dGhvcml0aWVzS2V5IjoiQWRtaW4iLCJleHAiOjE1NzAxNzQxMjJ9.n0IpWfS00PjZxwip0SuvqA5wBc3Bpn-dnKzJhkWw37pwTs1Svsfuc_kSjiYry1-fhtzKq8nB3TVAXzPsc3xp_w"
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
