package com.aleksandersh.weather.features.weather.data.model.transferable.current;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


/**
 * Created by AleksanderSh on 14.07.2017.
 */

public class CurrentWeatherDto {

    @SerializedName("coord")
    @Expose
    private LocationDto locationDto;

    @SerializedName("weather")
    @Expose
    private List<WeatherConditionDto> weatherConditionDtoList = null;

    @SerializedName("base")
    @Expose
    private String base;

    @SerializedName("main")
    @Expose
    private GeneralDto generalDto;

    @SerializedName("visibility")
    @Expose
    private Integer visibility;

    @SerializedName("wind")
    @Expose
    private WindDto windDto;

    @SerializedName("clouds")
    @Expose
    private CloudsDto cloudsDto;

    @SerializedName("dt")
    @Expose
    private Integer time;

    @SerializedName("sys")
    @Expose
    private SystemDto systemDto;

    @SerializedName("id")
    @Expose
    private Integer cityId;

    @SerializedName("name")
    @Expose
    private String cityName;

    @SerializedName("cod")
    @Expose
    private Integer cod;

    public LocationDto getLocation() {
        return locationDto;
    }

    public List<WeatherConditionDto> getWeatherConditions() {
        return weatherConditionDtoList;
    }

    public String getBase() {
        return base;
    }

    public GeneralDto getGeneral() {
        return generalDto;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public WindDto getWind() {
        return windDto;
    }

    public CloudsDto getClouds() {
        return cloudsDto;
    }

    public Integer getTime() {
        return time;
    }

    public SystemDto getSystem() {
        return systemDto;
    }

    public Integer getCityId() {
        return cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public Integer getCod() {
        return cod;
    }
}