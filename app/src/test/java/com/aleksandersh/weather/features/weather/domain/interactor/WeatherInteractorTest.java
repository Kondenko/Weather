package com.aleksandersh.weather.features.weather.domain.interactor;


import com.aleksandersh.weather.RxTestRule;
import com.aleksandersh.weather.features.city.data.model.storable.City;
import com.aleksandersh.weather.features.weather.data.model.CurrentWeatherDtoConverter;
import com.aleksandersh.weather.features.weather.data.model.ForecastDtoConverter;
import com.aleksandersh.weather.features.weather.data.model.storable.Weather;
import com.aleksandersh.weather.features.weather.data.model.storable.WeatherStorableState;
import com.aleksandersh.weather.features.weather.data.model.transferable.current.CurrentWeatherDto;
import com.aleksandersh.weather.features.weather.data.model.transferable.forecast.ForecastDto;
import com.aleksandersh.weather.features.weather.data.model.transferable.forecast.ForecastResultDto;
import com.aleksandersh.weather.features.weather.domain.service.CurrentWeatherService;
import com.aleksandersh.weather.features.weather.domain.service.ForecastService;
import com.aleksandersh.weather.features.weather.storage.WeatherDao;
import com.aleksandersh.weather.storage.SettingsDao;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Created by Vladimir Kondenko on 13.08.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class WeatherInteractorTest {

    @Rule
    public RxTestRule rxRule = new RxTestRule();

    @Mock
    CurrentWeatherService currentWeatherService;

    @Mock
    ForecastService forecastService;

    @Mock
    SettingsDao settingsDao;

    @Mock
    WeatherDao weatherDao;

    @Mock
    CurrentWeatherDtoConverter currentWeatherDtoConverter;

    @Mock
    ForecastDtoConverter forecastDtoConverter;

    WeatherInteractor interactor;

    @Before
    public void setUp() throws Exception {
        interactor = new WeatherInteractor(
                currentWeatherService,
                forecastService,
                settingsDao,
                weatherDao,
                currentWeatherDtoConverter,
                forecastDtoConverter);
    }

    @Test
    public void getCurrentWeather() throws Exception {
        WeatherStorableState databaseResult = mock(WeatherStorableState.class);
        CurrentWeatherDto networkResult = mock(CurrentWeatherDto.class);
        Weather weather = mock(Weather.class);

        when(settingsDao.getLocale()).thenReturn("en");
        when(settingsDao.getUnits()).thenReturn("metric");
        when(currentWeatherDtoConverter.convert(any(CurrentWeatherDto.class))).thenReturn(weather);
        when(weatherDao.getCurrentWeather())
                .thenReturn(Single.just(databaseResult));
        when(currentWeatherService.getCurrentWeatherByLocation(anyDouble(), anyDouble(), anyString(), anyString()))
                .thenReturn(Single.just(networkResult));

        Single<Weather> weatherSingle = interactor.getCurrentWeather(mock(City.class));
        weatherSingle.test()
                .assertNoErrors()
                .assertResult(weather);
        verify(weatherDao, atMost(1)).saveWeather(any(WeatherStorableState.class));
    }

    @Test
    public void getForecast() throws Exception {
        WeatherStorableState databaseResult = mock(WeatherStorableState.class);
        ForecastResultDto networkResult = mock(ForecastResultDto.class);
        ForecastDto forecast = mock(ForecastDto.class);
        List<ForecastDto> listForecast = new ArrayList<>();
        listForecast.add(forecast);
        Weather weather = mock(Weather.class);

        when(settingsDao.getLocale()).thenReturn("en");
        when(settingsDao.getUnits()).thenReturn("metric");
        when(forecastService.getForecast(anyDouble(), anyDouble(), anyString(), anyString()))
                .thenReturn(Single.just(networkResult));
        when(networkResult.getForecastDto()).thenReturn(listForecast);
        when(forecastDtoConverter.convert(forecast)).thenReturn(weather);
        when(weatherDao.getForecast())
                .thenReturn(Flowable.just(databaseResult));

        Observable<Weather> forecastObservable = interactor.getForecast(mock(City.class));
        forecastObservable.test()
                .assertNoErrors()
                .assertResult(weather);

    }

}