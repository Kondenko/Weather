package com.aleksandersh.weather.features.weather.di;


import com.aleksandersh.weather.features.weather.data.model.CurrentWeatherDtoConverter;
import com.aleksandersh.weather.features.weather.data.model.ForecastDtoConverter;
import com.aleksandersh.weather.features.weather.domain.service.CurrentWeatherService;
import com.aleksandersh.weather.features.weather.domain.service.ForecastService;
import com.aleksandersh.weather.features.weather.storage.WeatherDao;
import com.aleksandersh.weather.storage.AppDatabase;
import com.aleksandersh.weather.utils.Const;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;


/**
 * Created by Vladimir Kondenko on 02.08.17.
 */

@Module
public class WeatherModule {

    @Provides
    @Singleton
    public WeatherDao provideWeatherDao(AppDatabase database) {
        return database.weatherDao();
    }

    @Provides
    @Singleton
    public CurrentWeatherDtoConverter provideCurrentWeatherDtoConverter() {
        return new CurrentWeatherDtoConverter();
    }

    @Provides
    @Singleton
    public ForecastDtoConverter provideForecastDtoConverter() {
        return new ForecastDtoConverter();
    }

    @Provides
    @Singleton
    public CurrentWeatherService provideCurrentWeatherService(@Named(Const.DI_API_SCOPE_WEATHER) Retrofit retrofit) {
        return retrofit.create(CurrentWeatherService.class);
    }

    @Provides
    @Singleton
    public ForecastService provideForecastService(@Named(Const.DI_API_SCOPE_WEATHER) Retrofit retrofit) {
        return retrofit.create(ForecastService.class);
    }

}
