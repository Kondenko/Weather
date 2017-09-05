package com.aleksandersh.weather.features.weather.domain.service;


import com.aleksandersh.weather.features.weather.data.model.transferable.forecast.ForecastResultDto;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by Vladimir Kondenko on 11.08.17.
 */

public interface ForecastService {

    @GET("forecast")
    Single<ForecastResultDto> getForecast(
            @Query("lat") double latitude,
            @Query("lon") double longitude,
            @Query("units") String units,
            @Query("lang") String locale
    );

}
