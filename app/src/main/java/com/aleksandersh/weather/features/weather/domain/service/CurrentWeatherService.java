package com.aleksandersh.weather.features.weather.domain.service;


import com.aleksandersh.weather.features.weather.data.model.transferable.current.CurrentWeatherDto;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by AleksanderSh on 14.07.2017.
 * <p>
 * <p>
 * Определение интерфейса работы с сервисом Open weather map для библиотеки Retrofit 2.
 * Содержит методы для получения текущей погоды.
 */

public interface CurrentWeatherService {

    /**
     * Получение погоды в локации, заданной координатами.
     *
     * @param latitude  Широта.
     * @param longitude Долгота.
     * @param units     Единица измерения температур.
     * @return Ответ от сервера.
     */
    @GET("weather")
    Single<CurrentWeatherDto> getCurrentWeatherByLocation(
            @Query("lat") double latitude,
            @Query("lon") double longitude,
            @Query("units") String units,
            @Query("lang") String locale
    );

}
