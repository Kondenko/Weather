package com.aleksandersh.weather.features.weather.presentation;


import com.aleksandersh.weather.features.city.data.model.storable.City;
import com.aleksandersh.weather.features.city.domain.interactor.CityInteractor;
import com.aleksandersh.weather.features.weather.domain.interactor.WeatherInteractor;
import com.aleksandersh.weather.utils.BasePresenter;

import javax.inject.Inject;


/**
 * Created by AleksanderSh on 20.07.2017.
 */

public class WeatherPresenter extends BasePresenter<WeatherView> {

    private WeatherInteractor weatherInteractor;
    private CityInteractor cityInteractor;

    @Inject
    public WeatherPresenter(WeatherInteractor weatherInteractor, CityInteractor cityInteractor) {
        super();
        this.weatherInteractor = weatherInteractor;
        this.cityInteractor = cityInteractor;
    }

    public void onInit() {
        onUpdate();
    }

    public void onUpdate() {
        view.showLoading(true);
        add(cityInteractor.getCurrentCity()
                .subscribe(
                        city -> {
                            view.showCurrentCity(city.getName());
                            getCurrentWeather(city);
                            getForecast(city);
                        },
                        error -> {
                            view.showError(error);
                        })
        );
    }

    private void getCurrentWeather(City city) {
        weatherInteractor.getCurrentWeather(city)
                .doFinally(() -> view.showLoading(false))
                .subscribe(view::showCurrentWeather, view::showError);
    }

    private void getForecast(City city) {
        view.clearForecastList();
        weatherInteractor.getForecast(city)
                .doFinally(() -> view.showLoading(false))
                .subscribe(view::addForecast, view::showError);
    }

}
