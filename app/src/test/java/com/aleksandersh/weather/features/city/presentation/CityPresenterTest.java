package com.aleksandersh.weather.features.city.presentation;


import com.aleksandersh.weather.features.city.data.model.storable.City;
import com.aleksandersh.weather.features.city.domain.interactor.CityInteractor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import io.reactivex.Flowable;
import io.reactivex.Single;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;


/**
 * Created by Vladimir Kondenko on 30.07.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class CityPresenterTest {

    @Mock
    private CityView view;

    @Mock
    private CityInteractor interactor;

    private CityPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new CityPresenter(interactor);
        presenter.attach(view);
    }

    @After
    public void tearDown() throws Exception {
        presenter.detach();
    }

    @Test
    public void onInit() throws Exception {
        when(interactor.getCurrentCity()).thenReturn(Single.just(mock(City.class)));
        when(interactor.getSavedCities()).thenReturn(Flowable.just(mock(City.class)));
        presenter.onInit();
        verify(view, times(1)).showSearchState(false);
        verify(view, times(1)).showCurrentCity(any(City.class));
    }

    @Test
    public void onSuggestionClick() throws Exception {
        City city = mock(City.class);
        presenter.onSuggestionClick(city);
        verify(view, times(1)).showSearchState(false);
        verify(interactor).addCity(city);
        verify(view).showCurrentCity(city);
        verifyNoMoreInteractions(view);
        verifyNoMoreInteractions(interactor);
    }

    @Test
    public void onSavedCitiesRequested() throws Exception {
        when(interactor.getSavedCities()).thenReturn(Flowable.just(mock(City.class)));
        when(interactor.getCurrentCity()).thenReturn(Single.just(mock(City.class)));
        presenter.onSavedCitiesRequested();
        verify(view).addSavedCityToList(any(City.class));
        verify(view, times(1)).showCurrentCity(any(City.class));
        verifyNoMoreInteractions(view);
    }

    @Test
    public void onCitySelected() throws Exception {
        City city = mock(City.class);
        presenter.onSavedCityClick(city);
        verify(view).showCurrentCity(city);
        verifyNoMoreInteractions(view);
    }

    @Test
    public void onSearchQueryUpdated() throws Exception {
        ArrayList<City> cityMocks = new ArrayList<>();
        cityMocks.add(mock(City.class));
        cityMocks.add(mock(City.class));
        when(interactor.getSuggestions(anyString())).thenReturn(Single.just(cityMocks));
        presenter.onSearchQueryUpdated(anyString());
        verify(view).showSuggestions(cityMocks);
    }

    @Test
    public void onSavedCityClick() throws Exception {
        City city = mock(City.class);
        presenter.onSavedCityClick(city);
        verify(view, times(1)).showCurrentCity(city);
        verifyNoMoreInteractions(view);
    }

    @Test
    public void onSavedCityDeleted() throws Exception {
        City city = mock(City.class);
        presenter.onSavedCityDeleted(city);
        verify(interactor, times(1)).deleteCity(city);
        verifyNoMoreInteractions(interactor);
    }


}
