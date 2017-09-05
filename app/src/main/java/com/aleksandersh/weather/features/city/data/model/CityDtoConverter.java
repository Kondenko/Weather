package com.aleksandersh.weather.features.city.data.model;


import com.aleksandersh.weather.features.city.data.model.storable.City;
import com.aleksandersh.weather.features.city.data.model.storable.CurrentCity;
import com.aleksandersh.weather.features.city.data.model.transferable.CityDto;


/**
 * Created by Vladimir Kondenko on 25.07.17.
 */

public class CityDtoConverter {

    public City convert(CityDto dto) {
        double lng = Double.parseDouble(dto.getLng());
        double lat = Double.parseDouble(dto.getLat());
        return new City(dto.getName(), dto.getCountryName(), lat, lng);
    }

    public CurrentCity convert(City city) {
        return new CurrentCity(city);
    }

}
