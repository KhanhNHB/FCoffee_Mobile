package com.example.fcoffee.networks;

import com.example.fcoffee.modules.Account.repositories.LoginRepository;

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
                        LoginRepository.TOKEN
                );

                System.out.println(LoginRepository.TOKEN);
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
