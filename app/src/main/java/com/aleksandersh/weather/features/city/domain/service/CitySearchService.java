package com.aleksandersh.weather.features.city.domain.service;


import com.aleksandersh.weather.features.city.data.model.transferable.CityResultDto;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by Vladimir Kondenko on 22.07.17.
 */

public interface CitySearchService {

    /**
     * Поиск города в базе данных GeoNames.
     * <br>
     * TODO Добавить параметр fuzzy (&fuzzy=0.8) для коректировки опечаток (<a href="http://www.geonames.org/export/geonames-search.html">подробнее</a>) и lang для возвращения результата на нужном языке
     *
     * @param name название города или его часть.
     * @return список городов, в названии которых содержится параметр name.
     */
    @GET("searchJSON")
    Single<CityResultDto> searchByName(@Query("name") String name, @Query("name_startsWith") String startsWith, @Query("maxRows") int suggestionsNumber);

}
