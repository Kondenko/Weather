package com.aleksandersh.weather.network.httpClient.converter;

import com.aleksandersh.weather.model.city.City;
import com.aleksandersh.weather.network.dto.city.CityDto;

/**
 * Created by Vladimir Kondenko on 25.07.17.
 */

public class CityDtoConverter implements DtoConverter<City, CityDto> {

    @Override
    public City convert(CityDto dto) {
        double lng = Double.parseDouble(dto.getLng());
        double lat = Double.parseDouble(dto.getLat());
        return new City(dto.getCityId(), dto.getName(), dto.getCountryName(), lng, lat);
    }

}