
package com.aleksandersh.weather.features.weather.data.model.transferable.forecast;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForecastDto {

    @SerializedName("dt")
    @Expose
    private int dt;
    @SerializedName("main")
    @Expose
    private MainDto main;
    @SerializedName("weather")
    @Expose
    private java.util.List<WeatherDto> weather = null;
    @SerializedName("clouds")
    @Expose
    private CloudsDto cloudsDto;
    @SerializedName("wind")
    @Expose
    private WindDto wind;
    @SerializedName("rain")
    @Expose
    private RainDto rain;
    @SerializedName("sys")
    @Expose
    private SysDto sys;
    @SerializedName("dt_txt")
    @Expose
    private String dtTxt;

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public MainDto getMain() {
        return main;
    }

    public void setMain(MainDto main) {
        this.main = main;
    }

    public java.util.List<WeatherDto> getWeather() {
        return weather;
    }

    public void setWeather(java.util.List<WeatherDto> weather) {
        this.weather = weather;
    }

    public CloudsDto getCloudsDto() {
        return cloudsDto;
    }

    public void setCloudsDto(CloudsDto cloudsDto) {
        this.cloudsDto = cloudsDto;
    }

    public WindDto getWind() {
        return wind;
    }

    public void setWind(WindDto wind) {
        this.wind = wind;
    }

    public RainDto getRain() {
        return rain;
    }

    public void setRain(RainDto rain) {
        this.rain = rain;
    }

    public SysDto getSys() {
        return sys;
    }

    public void setSys(SysDto sys) {
        this.sys = sys;
    }

    public String getDtTxt() {
        return dtTxt;
    }

    public void setDtTxt(String dtTxt) {
        this.dtTxt = dtTxt;
    }

}