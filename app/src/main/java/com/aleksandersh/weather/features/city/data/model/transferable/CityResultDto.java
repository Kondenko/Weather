package com.aleksandersh.weather.features.city.data.model.transferable;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


/**
 * Created by Vladimir Kondenko on 22.07.17.
 */

public class CityResultDto implements Parcelable {

    @SerializedName("totalResultsCount")
    @Expose
    private int totalResultsCount;

    @SerializedName("geonames")
    @Expose
    private List<CityDto> cities = null;

    public final static Parcelable.Creator<CityResultDto> CREATOR = new Creator<CityResultDto>() {

        @SuppressWarnings({
                "unchecked"
        })
        public CityResultDto createFromParcel(Parcel in) {
            CityResultDto instance = new CityResultDto();
            instance.totalResultsCount = ((int) in.readValue((int.class.getClassLoader())));
            in.readList(instance.cities, (CityDto.class.getClassLoader()));
            return instance;
        }

        public CityResultDto[] newArray(int size) {
            return (new CityResultDto[size]);
        }

    };

    public int getTotalResultsCount() {
        return totalResultsCount;
    }

    public void setTotalResultsCount(int totalResultsCount) {
        this.totalResultsCount = totalResultsCount;
    }

    public List<CityDto> getCities() {
        return cities;
    }

    public void setCities(List<CityDto> cities) {
        this.cities = cities;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(totalResultsCount);
        dest.writeList(cities);
    }

    public int describeContents() {
        return 0;
    }

}