package com.aleksandersh.weather.features.weather.storage;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.aleksandersh.weather.features.weather.data.model.storable.WeatherStorableState;

import io.reactivex.Flowable;
import io.reactivex.Single;


/**
 * Created by AleksanderSh on 21.07.2017.
 * <p>
 * Интерфейс для обращения к хранилищу с данными.
 */

@Dao
public interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveWeather(WeatherStorableState storableState);

    @Query("SELECT * FROM saved_weather WHERE is_current=1")
    Single<WeatherStorableState> getCurrentWeather();

    @Query("SELECT * FROM saved_weather WHERE is_current=0")
    Flowable<WeatherStorableState> getForecast();

}
