package com.aleksandersh.weather.features.city.data.model.storable;


import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


/**
 * Created by Vladimir Kondenko on 12.08.17.
 *
 * Represents a table with a single row for the city which is currently selected.
 */

@Entity(tableName = "current_city")
public class CurrentCity {

    @PrimaryKey(autoGenerate = true)
    public int currentCityKey;

    @Embedded
    private City currentCity;

    public CurrentCity(City currentCity) {
        this.currentCity = currentCity;
    }

    public City getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(City currentCity) {
        this.currentCity = currentCity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrentCity that = (CurrentCity) o;
        if (currentCityKey != that.currentCityKey) return false;
        return currentCity != null ? currentCity.equals(that.currentCity) : that.currentCity == null;

    }

    @Override
    public int hashCode() {
        int result = currentCityKey;
        result = 31 * result + (currentCity != null ? currentCity.hashCode() : 0);
        return result;
    }
}
