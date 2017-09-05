package com.aleksandersh.weather.features.weather.data.model.storable;


/**
 * Created by AleksanderSh on 16.07.2017.
 * <p>
 * Запрос на обновление погоды по идентификатору города.
 */

public class WeatherRequest {

    private double lng;

    private double lat;

    private String units;

    private String locale;

    public WeatherRequest(double lng, double lat, String units, String locale) {
        this.lng = lng;
        this.lat = lat;
        this.units = units;
        this.locale = locale;
    }

    public String getUnits() {
        return units;
    }

    public String getLocale() {
        return locale;
    }

    public double getLng() {
        return lng;
    }

    public double getLat() {
        return lat;
    }

}
