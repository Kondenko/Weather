package com.aleksandersh.weather.features.weather.data.model;


import com.aleksandersh.weather.features.weather.data.model.storable.Weather;
import com.aleksandersh.weather.features.weather.data.model.transferable.forecast.ForecastDto;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.text.IsEmptyString.isEmptyOrNullString;
import static org.junit.Assert.fail;


/**
 * Created by Vladimir Kondenko on 13.08.17.
 */
@RunWith(JUnit4.class)
public class ForecastDtoConverterTest {

    private static PodamFactory podamFactory;

    private static ForecastDtoConverter converter;

    @BeforeClass
    public static void setUpClass() throws Exception {
        podamFactory = new PodamFactoryImpl();
        converter = new ForecastDtoConverter();
    }

    @Test
    public void shouldConvertTransferableToStorable() throws Exception {
        ForecastDto transferableForecast = podamFactory.manufacturePojo(ForecastDto.class);
        transferableForecast.getWeather().get(0).setId(205);
        Weather weather = converter.convert(transferableForecast);
        assertThat(weather.getTemperature(), closeTo(transferableForecast.getMain().getTemp(), 0));
        assertThat(weather.getPressure(), closeTo(transferableForecast.getMain().getPressure(), 0));
        assertThat(weather.getHumidity(), closeTo(transferableForecast.getMain().getHumidity(), 0));
        assertThat(weather.getCloudiness(), equalTo(transferableForecast.getCloudsDto().getCloudiness()));
        assertThat(weather.getWindSpeed(), closeTo(transferableForecast.getWind().getSpeed(), 0));
        assertThat(weather.getWindDirection(), closeTo(transferableForecast.getWind().getDirection(), 0));
        assertThat(weather.getDescription(), isEmptyOrNullString()); // No description for forecast
        assertThat(weather.getGroup(), is(not(isEmptyOrNullString())));
        assertThat(weather.getTimestamp(), equalTo(transferableForecast.getDt()));
        assertThat(weather.getDateReadable(), is(not(isEmptyOrNullString())));
    }

    @Test
    public void shouldThrowIllegalArgumentException() {
        int illegalId = 213414124;
        ForecastDto transferableForecast = podamFactory.manufacturePojo(ForecastDto.class);
        transferableForecast.getWeather().get(0).setId(illegalId);
        try {
            converter.convert(transferableForecast);
            fail("Should throw");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), containsString(String.valueOf(illegalId)));
        }
    }

}