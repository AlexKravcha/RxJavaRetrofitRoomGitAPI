package com.sashka.usergithubproj.Network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkClient {
    public static Retrofit retrofit;


    public static NetworkClientInterface getRetrofit() {
        if (retrofit == null) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(); // для ответа в лог с сервера
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY); // для ответа в лог с сервера
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addInterceptor(httpLoggingInterceptor); // для ответа в лог с сервера
            OkHttpClient okHttpClient = builder.build();

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }

        return retrofit.create(NetworkClientInterface.class);
    }
}
