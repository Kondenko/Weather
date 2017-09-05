package com.aleksandersh.weather.features.settings;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.aleksandersh.weather.App;
import com.aleksandersh.weather.R;

import javax.inject.Inject;


/**
 * Фрагмент, содержащий настройки приложения.
 */
public class SettingsFragment extends PreferenceFragmentCompat {

    @Inject
    SharedPreferences.OnSharedPreferenceChangeListener mChangeListener;

    /**
     * Создает новый экземпляр {@link SettingsFragment}.
     *
     * @return Новый экземпляр.
     */
    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App) getActivity().getApplication()).getAppComponent().inject(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(mChangeListener);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(mChangeListener);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }
}
