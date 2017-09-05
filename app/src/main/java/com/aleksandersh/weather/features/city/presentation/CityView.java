package com.aleksandersh.weather.features.city.presentation;


import com.aleksandersh.weather.features.city.data.model.storable.City;
import com.aleksandersh.weather.utils.BaseView;

import java.util.List;


public interface CityView extends BaseView {

    void showSuggestions(List<City> cities);

    void addSavedCityToList(City cities);

    void showCurrentCity(City city);

    void showSearchState(boolean show);

}
