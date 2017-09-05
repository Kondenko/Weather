package com.aleksandersh.weather.di.module;


import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;

import com.aleksandersh.weather.service.WeatherServiceScheduler;
import com.aleksandersh.weather.storage.AppDatabase;
import com.aleksandersh.weather.storage.SettingsChangeListener;
import com.aleksandersh.weather.storage.SettingsDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static com.aleksandersh.weather.utils.Const.DATABASE_NAME;


/**
 * Created by AleksanderSh on 21.07.2017.
 */

@Module
public class DataModule {

    @Provides
    @Singleton
    public AppDatabase provideDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME).build();
    }

    @Provides
    @Singleton
    public SettingsDao provideSettingsDao(Context context) {
        return new SettingsDao(context);
    }

    @Provides
    @Singleton
    public SharedPreferences.OnSharedPreferenceChangeListener provideSharedPreferenceChangeListener(
            Context context,
            WeatherServiceScheduler serviceScheduler) {
        return new SettingsChangeListener(context, serviceScheduler);
    }
}
