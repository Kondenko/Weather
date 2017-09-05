package com.aleksandersh.weather.features.weather.domain.interactor;


import com.aleksandersh.weather.features.city.data.model.storable.City;
import com.aleksandersh.weather.features.weather.data.model.CurrentWeatherDtoConverter;
import com.aleksandersh.weather.features.weather.data.model.ForecastDtoConverter;
import com.aleksandersh.weather.features.weather.data.model.storable.Weather;
import com.aleksandersh.weather.features.weather.data.model.storable.WeatherRequest;
import com.aleksandersh.weather.features.weather.data.model.storable.WeatherStorableState;
import com.aleksandersh.weather.features.weather.data.model.transferable.forecast.ForecastResultDto;
import com.aleksandersh.weather.features.weather.domain.service.CurrentWeatherService;
import com.aleksandersh.weather.features.weather.domain.service.ForecastService;
import com.aleksandersh.weather.features.weather.storage.WeatherDao;
import com.aleksandersh.weather.storage.SettingsDao;
import com.aleksandersh.weather.utils.Utils;

import java.util.Date;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;


/**
 * Created by Vladimir Kondenko on 03.08.17.
 */

public class WeatherInteractor {

    private CurrentWeatherService currentWeatherService;
    private ForecastService forecastService;
    private SettingsDao settingsDao;
    private WeatherDao weatherDao;
    private CurrentWeatherDtoConverter currentWeatherDtoConverter;
    private ForecastDtoConverter forecastDtoConverter;

    @Inject
    public WeatherInteractor(
            CurrentWeatherService currentWeatherService,
            ForecastService forecastService,
            SettingsDao settingsDao,
            WeatherDao weatherDao,
            CurrentWeatherDtoConverter currentWeatherDtoConverter, ForecastDtoConverter forecastDtoConverter) {
        this.currentWeatherService = currentWeatherService;
        this.forecastService = forecastService;
        this.settingsDao = settingsDao;
        this.weatherDao = weatherDao;
        this.currentWeatherDtoConverter = currentWeatherDtoConverter;
        this.forecastDtoConverter = forecastDtoConverter;
    }

    public Single<Weather> getCurrentWeather(City city) {
        String units = settingsDao.getUnits();
        String locale = settingsDao.getLocale();
        return currentWeatherService.getCurrentWeatherByLocation(city.getLat(), city.getLng(), units, locale)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(currentWeatherDtoConverter::convert)
                .doOnSuccess(weather -> save(weather, city.getLat(), city.getLng(), units, locale, true))
                .onErrorResumeNext(getSavedCurrentWeather());
    }

    public Observable<Weather> getForecast(City city) {
        String units = settingsDao.getUnits();
        String locale = settingsDao.getLocale();
        return forecastService.getForecast(city.getLat(), city.getLng(), units, locale)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(ForecastResultDto::getForecastDto)
                .flatMapObservable(Observable::fromIterable)
                .map(forecastDto -> forecastDtoConverter.convert(forecastDto))
                .doOnNext(weather -> save(weather, city.getLat(), city.getLng(), units, locale, false))
                .sorted((w1, w2) -> {
                    int timestamp1 = w1.getTimestamp();
                    int timestamp2 = w2.getTimestamp();
                    if (timestamp1 > timestamp2) return 1;
                    if (timestamp1 < timestamp2) return -1;
                    return 0;
                })
                .onErrorResumeNext(getSavedForecast())
                ;
    }

    private void save(Weather weather, double lat, double lng, String units, String locale, boolean isCurrent) {
        WeatherRequest request = new WeatherRequest(lng, lat, units, locale);
        Date updateTime = new Date(System.currentTimeMillis());
        WeatherStorableState storableState = new WeatherStorableState(weather, request, updateTime, isCurrent);
        Utils.transaction(() -> weatherDao.saveWeather(storableState));
    }

    private Single<Weather> getSavedCurrentWeather() {
        Timber.i("Getting saved weather");
        return weatherDao.getCurrentWeather()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(WeatherStorableState::getWeather);
    }

    private Observable<Weather> getSavedForecast() {
        Timber.i("Getting saved forecast");
        return weatherDao.getForecast()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(WeatherStorableState::getWeather)
                .toObservable();
    }


}
