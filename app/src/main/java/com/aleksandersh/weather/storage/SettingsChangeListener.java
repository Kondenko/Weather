package com.aleksandersh.weather.storage;


import android.content.Context;
import android.content.SharedPreferences;

import com.aleksandersh.weather.R;
import com.aleksandersh.weather.service.ServiceScheduler;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;


/**
 * Created by AleksanderSh on 16.07.2017.
 * <p>
 * Обработчик изменения настроек приложения.
 */

public class SettingsChangeListener implements SharedPreferences.OnSharedPreferenceChangeListener {

    private String serviceEnabledKey;

    private String serviceIntervalKey;

    private String serviceIntervalDefault;

    private ServiceScheduler serviceScheduler;

    @Inject
    public SettingsChangeListener(Context context, ServiceScheduler scheduler) {
        serviceScheduler = scheduler;

        serviceEnabledKey = context.getString(R.string.pref_service_enabled_key);
        serviceIntervalKey = context.getString(R.string.pref_service_interval_key);
        serviceIntervalDefault = context.getString(R.string.pref_service_interval_default);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(serviceEnabledKey) || key.equals(serviceIntervalKey)) {
            if (sharedPreferences.getBoolean(serviceEnabledKey, false)) {
                int interval = (int) TimeUnit.MINUTES.toSeconds(Integer.parseInt(
                        sharedPreferences.getString(serviceIntervalKey, serviceIntervalDefault)));
                serviceScheduler.startService(interval);
            } else {
                if (key.equals(serviceEnabledKey)) {
                    serviceScheduler.stopService();
                }
            }
        }
    }

}
