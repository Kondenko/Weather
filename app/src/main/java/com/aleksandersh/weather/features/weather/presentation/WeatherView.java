package com.aleksandersh.weather.features.weather.presentation;


import com.aleksandersh.weather.features.weather.data.model.storable.Weather;
import com.aleksandersh.weather.utils.BaseView;


/**
 * Created by Vladimir Kondenko on 02.08.17.
 */

public interface WeatherView extends BaseView {

    void showCurrentWeather(Weather weather);

    void addForecast(Weather forecast);

    void clearForecastList();

    void showCurrentCity(String name);

}
