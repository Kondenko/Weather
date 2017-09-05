package com.aleksandersh.weather.features.city.data.model;


import com.aleksandersh.weather.features.city.data.model.storable.City;
import com.aleksandersh.weather.features.city.data.model.storable.CurrentCity;
import com.aleksandersh.weather.features.city.data.model.transferable.CityDto;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;


/**
 * Created by Vladimir Kondenko on 13.08.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class CityDtoConverterTest {

    private static PodamFactory podamFactory;

    private static CityDtoConverter converter;

    @BeforeClass
    public static void setUpClass() throws Exception {
        podamFactory = new PodamFactoryImpl();
        converter = new CityDtoConverter();
    }

    @Test
    public void shouldConvertTransferableToStorable() throws Exception {
        // Filling a POJO with random values
        CityDto transferableCity = podamFactory.manufacturePojo(CityDto.class);
        /*
         Replacing randomly generated string values with numeric strings.
         But actually in the case of a wrong string this test should fail
         giving us a hint that we should handle such exceptions in the converter class.
         */
        transferableCity.setCountryId("505");
        transferableCity.setLat("123");
        transferableCity.setLng("123");
        City city = converter.convert(transferableCity);
        assertThat(city.getName(), equalTo(transferableCity.getName()));
        assertThat(city.getCountryName(), equalTo(transferableCity.getCountryName()));
        assertThat(city.getLat(), equalTo(Double.valueOf(transferableCity.getLat())));
        assertThat(city.getLat(), equalTo(Double.valueOf(transferableCity.getLat())));
        assertThat(city.getLng(), equalTo(Double.valueOf(transferableCity.getLng())));
    }

    @Test
    public void shouldConvertStorableToTransferable() throws Exception {
        City cityMock = mock(City.class);
        CurrentCity currentCity = converter.convert(cityMock);
        assertThat(currentCity.getCurrentCity(), equalTo(cityMock));
    }

}