package com.aleksandersh.weather.features.weather.data.model.transferable.current;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by AleksanderSh on 14.07.2017.
 */

public class GeneralDto {

    @SerializedName("temp")
    @Expose
    private Double temperature;

    @SerializedName("pressure")
    @Expose
    private Double pressure;

    @SerializedName("humidity")
    @Expose
    private Integer humidity;

    @SerializedName("temp_min")
    @Expose
    private Double mMinimumTemperature;

    @SerializedName("temp_max")
    @Expose
    private Double maximumTemperature;

    public Double getTemperature() {
        return temperature;
    }

    public Double getPressure() {
        return pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public Double getMinimumTemperature() {
        return mMinimumTemperature;
    }

    public Double getMaximumTemperature() {
        return maximumTemperature;
    }
}