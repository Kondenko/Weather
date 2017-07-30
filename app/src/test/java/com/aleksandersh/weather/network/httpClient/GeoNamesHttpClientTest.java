package com.aleksandersh.weather.network.httpClient;

import com.aleksandersh.weather.RxJavaRule;
import com.aleksandersh.weather.network.dto.city.CityResultDto;
import com.aleksandersh.weather.network.httpService.CityHttpService;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import io.reactivex.Single;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Vladimir Kondenko on 30.07.17.
 */
public class GeoNamesHttpClientTest {

    @Rule
    public RxJavaRule rxJavaRule = new RxJavaRule();

    private CityHttpService service;
    private GeoNamesHttpClient client;

    @Before
    public void setUp() throws Exception {
        service = mock(CityHttpService.class);
        client = new GeoNamesHttpClient(service);
    }

    @Test
    public void getCity() throws Exception {
        CityResultDto resultDto = new CityResultDto();
        when(service.searchByName("Moscow", "Moscow", 7)).thenReturn(Single.just(resultDto));
        verify(client.getCity("Moscow")).test().assertSubscribed().assertComplete().assertNoErrors();
    }

}