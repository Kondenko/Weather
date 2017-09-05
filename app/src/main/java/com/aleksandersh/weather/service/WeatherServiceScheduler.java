package com.aleksandersh.weather.service;


import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;

import javax.inject.Inject;


/**
 * Created by AleksanderSh on 18.07.2017.
 * <p>
 * Реализация планировщика задачи по обновлению погоды с помощью Firebase Job Dispatcher.
 */

public class WeatherServiceScheduler implements ServiceScheduler {

    private static final String TAG = "WeatherServiceScheduler";

    private static final int SYNC_FLEXTIME_SECONDS = 30;

    private FirebaseJobDispatcher mDispatcher;

    @Inject
    public WeatherServiceScheduler(FirebaseJobDispatcher jobDispatcher) {
        mDispatcher = jobDispatcher;
    }

    /**
     * Запускает сервис с заданным интервалом.
     *
     * @param interval Интервал (в секундах).
     */
    @Override
    public void startService(int interval) {
        Job job = mDispatcher.newJobBuilder()
                .setService(WeatherUpdatingJobService.class)
                .setTag(WeatherUpdatingJobService.TAG)
                .setRecurring(true)
                .setLifetime(Lifetime.FOREVER)
                .setReplaceCurrent(true)
                .setTrigger(Trigger.executionWindow(interval, interval + SYNC_FLEXTIME_SECONDS))
                .setRetryStrategy(RetryStrategy.DEFAULT_EXPONENTIAL)
                .setConstraints(Constraint.ON_ANY_NETWORK)
                .build();
        mDispatcher.mustSchedule(job);
    }

    @Override
    public void stopService() {
        mDispatcher.cancel(WeatherUpdatingJobService.TAG);
    }

}
