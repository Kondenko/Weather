package com.aleksandersh.weather.features.weather.data.model.storable;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;


/**
 * Created by AleksanderSh on 16.07.2017.
 * <p>
 * Сохраненное состояние погоды. Используется при работе с хранилищем.
 */

@Entity(tableName = "saved_weather")
public class WeatherStorableState {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @Embedded
    private Weather weather;

    @Embedded
    private WeatherRequest request;

    @Embedded
    private Date date;

    @ColumnInfo(name = "is_current")
    private boolean isCurrent;

    public WeatherStorableState(Weather weather, WeatherRequest request, Date date, boolean isCurrent) {
        this.weather = weather;
        this.request = request;
        this.date = date;
        this.isCurrent = isCurrent;
    }

    public boolean isCurrent() {
        return isCurrent;
    }

    public Weather getWeather() {
        return weather;
    }

    public WeatherRequest getRequest() {
        return request;
    }

    public Date getDate() {
        return new Date(date.getTime());
    }
}
