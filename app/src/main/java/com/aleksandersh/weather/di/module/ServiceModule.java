package com.aleksandersh.weather.di.module;


import android.content.Context;

import com.aleksandersh.weather.service.ServiceScheduler;
import com.aleksandersh.weather.service.WeatherServiceScheduler;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * Created by AleksanderSh on 21.07.2017.
 */

@Module
public class ServiceModule {

    @Provides
    @Singleton
    public FirebaseJobDispatcher provideFirebaseJobDispatcher(Context context) {
        return new FirebaseJobDispatcher(new GooglePlayDriver(context));
    }

    @Provides
    @Singleton
    public ServiceScheduler provideServiceScheduler(FirebaseJobDispatcher jobDispatcher) {
        return new WeatherServiceScheduler(jobDispatcher);
    }
}
