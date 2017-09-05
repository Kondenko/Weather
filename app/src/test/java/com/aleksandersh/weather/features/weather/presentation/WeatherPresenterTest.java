package com.aleksandersh.weather.features.weather.presentation;


import com.aleksandersh.weather.features.city.data.model.storable.City;
import com.aleksandersh.weather.features.city.domain.interactor.CityInteractor;
import com.aleksandersh.weather.features.weather.data.model.storable.Weather;
import com.aleksandersh.weather.features.weather.domain.interactor.WeatherInteractor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;
import io.reactivex.Single;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;


/**
 * Created by Vladimir Kondenko on 13.08.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class WeatherPresenterTest {

    @Mock
    WeatherView view;

    @Mock
    WeatherInteractor weatherInteractor;

    @Mock
    CityInteractor cityInteractor;

    WeatherPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new WeatherPresenter(weatherInteractor, cityInteractor);
        presenter.attach(view);
    }

    @After
    public void tearDown() throws Exception {
        presenter.detach();
    }

    @Test
    public void onUpdate() throws Exception {
        City city = mock(City.class);
        Weather currentWeather = mock(Weather.class);
        Weather forecastWeather1 = mock(Weather.class);
        Weather forecastWeather2 = mock(Weather.class);
        when(cityInteractor.getCurrentCity()).thenReturn(Single.just(city));
        when(weatherInteractor.getCurrentWeather(city)).thenReturn(Single.just(currentWeather));
        when(weatherInteractor.getForecast(city)).thenReturn(Observable.fromArray(forecastWeather1, forecastWeather2));
        presenter.onUpdate();
        verify(cityInteractor, times(1)).getCurrentCity();
        verifyNoMoreInteractions(cityInteractor);
        verify(view, times(1)).showCurrentWeather(currentWeather);
        verify(view, atLeastOnce()).addForecast(any(Weather.class));
        verify(view, never()).showError(any());
    }

}