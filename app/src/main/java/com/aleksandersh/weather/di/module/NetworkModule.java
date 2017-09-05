package com.aleksandersh.weather.di.module;


import com.aleksandersh.weather.BuildConfig;
import com.aleksandersh.weather.network.interceptors.ApiKeyInterceptor;
import com.aleksandersh.weather.utils.Const;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by AleksanderSh on 21.07.2017.
 */

@Module
public class NetworkModule {

    @Provides
    @Singleton
    public GsonConverterFactory provideConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    public RxJava2CallAdapterFactory provideCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @Singleton
    @Named(Const.DI_API_SCOPE_CITY)
    public ApiKeyInterceptor provideCityApiKeyInterceptor() {
        return new ApiKeyInterceptor(Const.API_KEY_PARAM_CITY, Const.API_KEY_CITY);
    }

    @Provides
    @Singleton
    @Named(Const.DI_API_SCOPE_WEATHER)
    public ApiKeyInterceptor provideWeatherApiKeyInterceptor() {
        return new ApiKeyInterceptor(Const.API_KEY_PARAM_WEATHER, Const.API_KEY_WEATHER);
    }


    @Provides
    @Singleton
    public StethoInterceptor provideStethoInterceptor() {
        return new StethoInterceptor();
    }

    @Provides
    @Singleton
    @Named(Const.DI_API_SCOPE_CITY)
    public OkHttpClient provideCityHttpClient(@Named(Const.DI_API_SCOPE_CITY) ApiKeyInterceptor apiKeyInterceptor, StethoInterceptor stethoInterceptor) {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                .addInterceptor(apiKeyInterceptor);
        if (BuildConfig.DEBUG) clientBuilder.addNetworkInterceptor(stethoInterceptor);
        return clientBuilder.build();
    }

    @Provides
    @Singleton
    @Named(Const.DI_API_SCOPE_WEATHER)
    public OkHttpClient provideWeatherHttpClient(@Named(Const.DI_API_SCOPE_WEATHER) ApiKeyInterceptor apiKeyInterceptor, StethoInterceptor stethoInterceptor) {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                .addInterceptor(apiKeyInterceptor);
        if (BuildConfig.DEBUG) clientBuilder.addNetworkInterceptor(stethoInterceptor);
        return clientBuilder.build();
    }

    @Provides
    @Singleton
    @Named(Const.DI_API_SCOPE_CITY)
    public Retrofit provideCityRetrofit(@Named(Const.DI_API_SCOPE_CITY) OkHttpClient client, GsonConverterFactory converterFactory, RxJava2CallAdapterFactory callAdapterFactory) {
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(Const.BASE_URL_CITY)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(callAdapterFactory)
                .build();
    }

    @Provides
    @Singleton
    @Named(Const.DI_API_SCOPE_WEATHER)
    public Retrofit provideWeatherRetrofit(@Named(Const.DI_API_SCOPE_WEATHER) OkHttpClient client, GsonConverterFactory converterFactory, RxJava2CallAdapterFactory callAdapterFactory) {
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(Const.BASE_URL_WEATHER)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(callAdapterFactory)
                .build();
    }

}
