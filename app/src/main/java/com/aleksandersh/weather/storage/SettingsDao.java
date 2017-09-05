package com.aleksandersh.weather.storage;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v7.preference.PreferenceManager;

import com.aleksandersh.weather.R;

import java.util.Locale;
import java.util.function.Consumer;


/**
 * Created by AleksanderSh on 19.07.2017.
 * <p>
 * Облегчает работу с настройками.
 */

public class SettingsDao {

    private Context context;

    private SharedPreferences defaultPreferences;

    private String serviceEnabledKey;

    private String serviceIntervalKey;

    private String serviceIntervalDefault;

    public SettingsDao(Context context) {
        this.context = context;
        defaultPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        serviceEnabledKey = context.getString(R.string.pref_service_enabled_key);
        serviceIntervalKey = context.getString(R.string.pref_service_interval_key);
        serviceIntervalDefault = context.getString(R.string.pref_service_interval_default);
    }

    public boolean isServiceEnabled() {
        return defaultPreferences.getBoolean(serviceEnabledKey, false);
    }

    /**
     * Интервал, указанный в настройках (в минутах).
     *
     * @return Интервал.
     */
    public int getServiceInterval() {
        return Integer.parseInt(defaultPreferences.getString(serviceIntervalKey,
                serviceIntervalDefault));
    }

    public String getLocale() {
        return Locale.getDefault().getLanguage();
    }

    public String getUnits() {
        String unitsKey = context.getString(R.string.pref_units_key);
        String unitsDefault = context.getString(R.string.pref_units_default);
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(unitsKey, unitsDefault);
    }

    private void put(Consumer<Editor> f) {
        Editor editor = defaultPreferences.edit();
        f.accept(editor);
        editor.apply();
    }

}
