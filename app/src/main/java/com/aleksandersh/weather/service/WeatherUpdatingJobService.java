package com.aleksandersh.weather.service;


import com.aleksandersh.weather.App;
import com.aleksandersh.weather.features.city.domain.interactor.CityInteractor;
import com.aleksandersh.weather.features.weather.domain.interactor.WeatherInteractor;
import com.aleksandersh.weather.utils.Utils;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;


/**
 * Created by AleksanderSh on 18.07.2017.
 * <p>
 * Сервис для запуска задачи обновления погоды.
 */

public class WeatherUpdatingJobService extends JobService {

    static final String TAG = "WeatherUpdatingJobS";

    @Inject
    WeatherInteractor weatherInteractor;

    @Inject
    CityInteractor cityInteractor;

    private Disposable weatherDisposable;

    @Override
    public void onCreate() {
        super.onCreate();
        App.getAppComponent().inject(this);
    }

    @Override
    public boolean onStartJob(final JobParameters job) {
        weatherDisposable = cityInteractor.getCurrentCity()
                .subscribe(city -> weatherInteractor.getCurrentWeather(city).subscribe());
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        Utils.dispose(weatherDisposable);
        return true;
    }

}
