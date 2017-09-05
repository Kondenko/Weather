package com.aleksandersh.weather.features.city.data.model.transferable;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by Vladimir Kondenko on 22.07.17.
 */
public class CityDto implements Parcelable {

    @SerializedName("adminCode1")
    @Expose
    private String adminCode1;

    @SerializedName("lng")
    @Expose
    private String lng;

    @SerializedName("geonameId")
    @Expose
    private int geonameId;

    @SerializedName("toponymName")
    @Expose
    private String toponymName;

    @SerializedName("countryId")
    @Expose
    private String countryId;

    @SerializedName("fcl")
    @Expose
    private String fcl;

    @SerializedName("population")
    @Expose
    private int population;

    @SerializedName("countryCode")
    @Expose
    private String countryCode;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("fclName")
    @Expose
    private String fclName;

    @SerializedName("countryName")
    @Expose
    private String countryName;

    @SerializedName("fcodeName")
    @Expose
    private String fcodeName;

    @SerializedName("adminName1")
    @Expose
    private String adminName1;

    @SerializedName("lat")
    @Expose
    private String lat;

    @SerializedName("fcode")
    @Expose
    private String fcode;

    public final static Parcelable.Creator<CityDto> CREATOR = new Creator<CityDto>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CityDto createFromParcel(Parcel in) {
            CityDto instance = new CityDto();
            instance.adminCode1 = ((String) in.readValue((String.class.getClassLoader())));
            instance.lng = ((String) in.readValue((String.class.getClassLoader())));
            instance.geonameId = ((int) in.readValue((int.class.getClassLoader())));
            instance.toponymName = ((String) in.readValue((String.class.getClassLoader())));
            instance.countryId = ((String) in.readValue((String.class.getClassLoader())));
            instance.fcl = ((String) in.readValue((String.class.getClassLoader())));
            instance.population = ((int) in.readValue((int.class.getClassLoader())));
            instance.countryCode = ((String) in.readValue((String.class.getClassLoader())));
            instance.name = ((String) in.readValue((String.class.getClassLoader())));
            instance.fclName = ((String) in.readValue((String.class.getClassLoader())));
            instance.countryName = ((String) in.readValue((String.class.getClassLoader())));
            instance.fcodeName = ((String) in.readValue((String.class.getClassLoader())));
            instance.adminName1 = ((String) in.readValue((String.class.getClassLoader())));
            instance.lat = ((String) in.readValue((String.class.getClassLoader())));
            instance.fcode = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public CityDto[] newArray(int size) {
            return (new CityDto[size]);
        }

    };

    public String getAdminCode1() {
        return adminCode1;
    }

    public void setAdminCode1(String adminCode1) {
        this.adminCode1 = adminCode1;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public int getCityId() {
        return geonameId;
    }

    public void setGeonameId(int geonameId) {
        this.geonameId = geonameId;
    }

    public String getToponymName() {
        return toponymName;
    }

    public void setToponymName(String toponymName) {
        this.toponymName = toponymName;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getFcl() {
        return fcl;
    }

    public void setFcl(String fcl) {
        this.fcl = fcl;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFclName() {
        return fclName;
    }

    public void setFclName(String fclName) {
        this.fclName = fclName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getFcodeName() {
        return fcodeName;
    }

    public void setFcodeName(String fcodeName) {
        this.fcodeName = fcodeName;
    }

    public String getAdminName1() {
        return adminName1;
    }

    public void setAdminName1(String adminName1) {
        this.adminName1 = adminName1;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getFcode() {
        return fcode;
    }

    public void setFcode(String fcode) {
        this.fcode = fcode;
    }

    @Override
    public String toString() {
        return String.format("%s, %s", name, countryCode);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(adminCode1);
        dest.writeValue(lng);
        dest.writeValue(geonameId);
        dest.writeValue(toponymName);
        dest.writeValue(countryId);
        dest.writeValue(fcl);
        dest.writeValue(population);
        dest.writeValue(countryCode);
        dest.writeValue(name);
        dest.writeValue(fclName);
        dest.writeValue(countryName);
        dest.writeValue(fcodeName);
        dest.writeValue(adminName1);
        dest.writeValue(lat);
        dest.writeValue(fcode);
    }

    public int describeContents() {
        return 0;
    }

}

