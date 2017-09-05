package com.aleksandersh.weather.storage;


import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.aleksandersh.weather.features.city.data.dao.CityDao;
import com.aleksandersh.weather.features.city.data.model.storable.City;
import com.aleksandersh.weather.features.city.data.model.storable.CurrentCity;
import com.aleksandersh.weather.features.weather.data.model.storable.WeatherStorableState;
import com.aleksandersh.weather.features.weather.storage.WeatherDao;


/**
 * Created by Vladimir Kondenko on 08.08.17.
 */

@android.arch.persistence.room.Database(version = 1, entities = {
        City.class,
        CurrentCity.class,
        WeatherStorableState.class,
})
@TypeConverters(Converters.class)
public abstract class AppDatabase extends RoomDatabase {

    public abstract CityDao cityDao();

    public abstract WeatherDao weatherDao();

}
