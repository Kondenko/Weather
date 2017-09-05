package com.aleksandersh.weather.features.city.di;


import com.aleksandersh.weather.features.city.data.dao.CityDao;
import com.aleksandersh.weather.features.city.data.model.CityDtoConverter;
import com.aleksandersh.weather.features.city.domain.interactor.CityInteractor;
import com.aleksandersh.weather.features.city.domain.interactor.CityInteractorImpl;
import com.aleksandersh.weather.features.city.domain.service.CitySearchService;
import com.aleksandersh.weather.features.city.presentation.CityPresenter;
import com.aleksandersh.weather.storage.AppDatabase;
import com.aleksandersh.weather.utils.Const;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;


/**
 * Created by Vladimir Kondenko on 23.07.17.
 */
@Module
public class CityModule {

    @Provides
    @Singleton
    public CityDao provideCityDao(AppDatabase database) {
        return database.cityDao();
    }

    @Provides
    @Singleton
    public CityDtoConverter provideCityDtoConverter() {
        return new CityDtoConverter();
    }


    @Provides
    @Singleton
    public CitySearchService provideService(@Named(Const.DI_API_SCOPE_CITY) Retrofit retrofit) {
        return retrofit.create(CitySearchService.class);
    }

    @Provides
    @Singleton
    public CityInteractor provideInteractor(CitySearchService service, CityDao dao, CityDtoConverter dtoConverter) {
        return new CityInteractorImpl(service, dao, dtoConverter);
    }

    @Provides
    public CityPresenter provideCityPresenter(CityInteractor interactor) {
        return new CityPresenter(interactor);
    }

}
