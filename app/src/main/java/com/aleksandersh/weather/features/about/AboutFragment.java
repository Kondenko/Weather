package com.aleksandersh.weather.features.about;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aleksandersh.weather.R;


/**
 * Фрагмент, содержащий информацию о приложении.
 */
public class AboutFragment extends Fragment {

    /**
     * Создает новый экземпляр фрагмента {@link AboutFragment}.
     *
     * @return Новый экземпляр.
     */
    public static AboutFragment newInstance() {
        return new AboutFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about, container, false);
    }
}
