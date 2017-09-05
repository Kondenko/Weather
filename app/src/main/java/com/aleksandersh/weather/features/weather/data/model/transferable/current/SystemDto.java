package com.aleksandersh.weather.features.weather.data.model.transferable.current;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by AleksanderSh on 14.07.2017.
 */

public class SystemDto {

    @SerializedName("type")
    @Expose
    private Integer mType;

    @SerializedName("id")
    @Expose
    private Integer mId;

    @SerializedName("message")
    @Expose
    private Double message;

    @SerializedName("country")
    @Expose
    private String countryCode;

    @SerializedName("sunrise")
    @Expose
    private Integer sunriseTime;

    @SerializedName("sunset")
    @Expose
    private Integer sunsetTime;

    public Integer getType() {
        return mType;
    }

    public Integer getId() {
        return mId;
    }

    public Double getMessage() {
        return message;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public Integer getSunriseTime() {
        return sunriseTime;
    }

    public Integer getSunsetTime() {
        return sunsetTime;
    }
}
