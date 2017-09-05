package com.aleksandersh.weather;


import android.app.Application;

import com.aleksandersh.weather.di.component.AppComponent;
import com.aleksandersh.weather.di.component.DaggerAppComponent;
import com.aleksandersh.weather.di.module.AppModule;
import com.aleksandersh.weather.di.module.DataModule;
import com.aleksandersh.weather.di.module.NetworkModule;
import com.aleksandersh.weather.di.module.ServiceModule;
import com.aleksandersh.weather.features.city.di.CityModule;
import com.aleksandersh.weather.features.weather.di.WeatherModule;
import com.facebook.stetho.Stetho;

import timber.log.Timber;


/**
 * Created by AleksanderSh on 21.07.2017.
 * <p>
 * Внедрен компонент Dagger.
 */

public class App extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
//        if (LeakCanary.isInAnalyzerProcess(this)) return;
        if (com.aleksandersh.weather.BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            Stetho.initializeWithDefaults(this);
//            LeakCanary.install(this);
        }
        appComponent = buildAppComponent();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    protected AppComponent buildAppComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .serviceModule(new ServiceModule())
                .dataModule(new DataModule())
                .weatherModule(new WeatherModule())
                .cityModule(new CityModule())
                .build();
    }
}
