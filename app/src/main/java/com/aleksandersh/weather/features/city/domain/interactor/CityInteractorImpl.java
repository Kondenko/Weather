package com.aleksandersh.weather.features.city.domain.interactor;


import com.aleksandersh.weather.features.city.data.dao.CityDao;
import com.aleksandersh.weather.features.city.data.model.CityDtoConverter;
import com.aleksandersh.weather.features.city.data.model.storable.City;
import com.aleksandersh.weather.features.city.data.model.storable.CurrentCity;
import com.aleksandersh.weather.features.city.data.model.transferable.CityResultDto;
import com.aleksandersh.weather.features.city.domain.service.CitySearchService;
import com.aleksandersh.weather.utils.Const;
import com.aleksandersh.weather.utils.Utils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;


/**
 * Created by Vladimir Kondenko on 23.07.17.
 */

public class CityInteractorImpl implements CityInteractor {

    private CitySearchService service;
    private CityDao dao;
    private CityDtoConverter converter;

    @Inject
    public CityInteractorImpl(CitySearchService service, CityDao cityDao, CityDtoConverter cityDtoConverter) {
        this.service = service;
        this.dao = cityDao;
        this.converter = cityDtoConverter;
    }

    @Override
    public Single<List<City>> getSuggestions(String name) {
        return service.searchByName(name, name, Const.DEFAULT_SUGGESTIONS_NUMBER)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(CityResultDto::getCities)
                .toObservable()
                .flatMap(Observable::fromIterable)
                .map(converter::convert)
                .distinct(City::getName)
                .distinct(City::getCountryName)
                .toList();
    }

    @Override
    public Flowable<City> getSavedCities() {
        return dao.getSavedCities()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .defaultIfEmpty(getDefaultCityObject())
                ;
    }

    @Override
    public Single<City> getCurrentCity() {
        Timber.i("Getting current city");
        return dao.getCurrentCity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(CurrentCity::getCurrentCity)
                .defaultIfEmpty(getDefaultCityObject())
                .toSingle()
                ;
    }

    @Override
    public void updateCurrentCity(City city) {
        Utils.transaction(() -> dao.updateCurrentCity(converter.convert(city)));
    }

    @Override
    public void addCity(City city) {
        Utils.transaction(() -> dao.addCity(city));
    }

    @Override
    public void deleteCity(City city) {
        Utils.transaction(() -> dao.deleteCity(city));
    }

    // An ugly way to return Moscow as a default city
    private Single<City> getDefaultCity() {
        Timber.i("Using default city");
        return getSuggestions("Moscow")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toObservable()
                .flatMap(Observable::fromIterable)
                .take(1)
                .doOnNext(this::addCity)
                .doOnNext(this::updateCurrentCity)
                .singleOrError()
                ;
    }

    private City getDefaultCityObject() {
        City defaultCity = new City("Moscow", "Russia", 55.76167, 37.60667);
        addCity(defaultCity);
        return defaultCity;
    }

}
