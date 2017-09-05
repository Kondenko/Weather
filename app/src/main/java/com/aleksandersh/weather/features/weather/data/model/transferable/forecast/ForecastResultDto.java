
package com.aleksandersh.weather.features.weather.data.model.transferable.forecast;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ForecastResultDto {

    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("message")
    @Expose
    private float message;
    @SerializedName("cnt")
    @Expose
    private int cnt;
    @SerializedName("list")
    @Expose
    private java.util.List<ForecastDto> forecastDto = null;
    @SerializedName("city")
    @Expose
    private CityDto cityDto;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public float getMessage() {
        return message;
    }

    public void setMessage(float message) {
        this.message = message;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public java.util.List<ForecastDto> getForecastDto() {
        return forecastDto;
    }

    public void setForecastDto(java.util.List<ForecastDto> forecastDto) {
        this.forecastDto = forecastDto;
    }

    public CityDto getCityDto() {
        return cityDto;
    }

    public void setCityDto(CityDto cityDto) {
        this.cityDto = cityDto;
    }

}
