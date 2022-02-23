package com.example.dukanactivity;

import android.content.Context;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {



    private static Retrofit getRetrofit() {


        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

//        int cacheSize = 10 * 1024 * 1024; // 10 MB
//        Cache cache = new Cache(context.getCacheDir(), cacheSize);
        OkHttpClient  okhttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();




        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fakestoreapi.com/").addConverterFactory(GsonConverterFactory.create())
                .client(okhttpClient)
                .build();

        return retrofit;

    }


}
