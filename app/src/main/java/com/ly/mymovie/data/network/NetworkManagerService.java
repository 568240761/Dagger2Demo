package com.ly.mymovie.data.network;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by LanYang on 2017/12/12.
 * Email:568240761@qq.com
 * 创建 Retrofit 实例
 */

public class NetworkManagerService {
    private final String BASE_URL = "https://api.douban.com/";

    public Request createRetrofit() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor())//打印日志
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)// 设置网络请求的Url地址
                .callFactory(client)
                .addConverterFactory(GsonConverterFactory.create())// 设置数据解析器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())// 支持RxJava平台
                .build();
        return retrofit.create(Request.class);
    }
}
