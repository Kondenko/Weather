package com.aleksandersh.weather.network.httpClient;

import com.aleksandersh.weather.model.city.CityResultWrapper;
import com.aleksandersh.weather.model.city.City;
import com.aleksandersh.weather.network.httpService.CityHttpService;
import com.aleksandersh.weather.utils.Const;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Vladimir Kondenko on 23.07.17.
 */

public class GeoNamesHttpClient implements CityHttpClient {

    private CityHttpService service;

    @Inject
    public GeoNamesHttpClient(CityHttpService service) {
        this.service = service;
    }

    @Override
    public Single<List<City>> getCity(String name) {
        return service.searchByName(name, Const.DEFAULT_SUGGESTIONS_NUMBER)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(CityResultWrapper::getCities);
    }

}