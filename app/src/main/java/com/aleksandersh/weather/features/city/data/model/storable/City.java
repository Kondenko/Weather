package com.aleksandersh.weather.features.city.data.model.storable;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


/**
 * Created by Vladimir Kondenko on 25.07.17.
 */

@Entity(tableName = "city")
public class City {

    @PrimaryKey(autoGenerate = true)
    public int id;

    private String name;

    private String countryName;

    private double lng;

    private double lat;

    public City(String name, String countryName, double lat, double lng) {
        this.name = name;
        this.countryName = countryName;
        this.lng = lng;
        this.lat = lat;
    }

    public String getName() {
        return name;
    }

    public String getCountryName() {
        return countryName;
    }

    public double getLng() {
        return lng;
    }

    public double getLat() {
        return lat;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        if (id != city.id) return false;
        if (Double.compare(city.lng, lng) != 0) return false;
        if (Double.compare(city.lat, lat) != 0) return false;
        if (name != null ? !name.equals(city.name) : city.name != null) return false;
        return countryName != null ? countryName.equals(city.countryName) : city.countryName == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (countryName != null ? countryName.hashCode() : 0);
        temp = Double.doubleToLongBits(lng);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lat);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return String.format("City (%d): %s, %s", id, name, countryName);
    }
}