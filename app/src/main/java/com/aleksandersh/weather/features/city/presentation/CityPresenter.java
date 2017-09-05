package com.aleksandersh.weather.features.city.presentation;


import com.aleksandersh.weather.features.city.data.model.storable.City;
import com.aleksandersh.weather.features.city.domain.interactor.CityInteractor;
import com.aleksandersh.weather.utils.BasePresenter;

import javax.inject.Inject;


/**
 * Created by Vladimir Kondenko on 23.07.17.
 */

public class CityPresenter extends BasePresenter<CityView> {

    private CityInteractor interactor;

    @Inject
    public CityPresenter(CityInteractor interactor) {
        super();
        this.interactor = interactor;
    }

    public void onInit() {
        view.showSearchState(false);
        onSavedCitiesRequested();
    }

    public void onSearchQueryUpdated(String name) {
        add(interactor.getSuggestions(name).subscribe(view::showSuggestions, view::showError));
    }

    public void onSavedCitiesRequested() {
        add(interactor.getSavedCities().subscribe(view::addSavedCityToList, view::showError));
        add(interactor.getCurrentCity().subscribe(view::showCurrentCity, view::showError));
    }

    public void onSuggestionClick(City city) {
        interactor.addCity(city);
        view.showSearchState(false);
        view.showCurrentCity(city);
    }

    public void onSavedCityClick(City city) {
        interactor.updateCurrentCity(city);
        view.showCurrentCity(city);
    }

    public void onSavedCityDeleted(City city) {
        interactor.deleteCity(city);
    }

}
