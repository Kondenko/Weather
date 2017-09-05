
package com.aleksandersh.weather.features.weather.data.model.transferable.forecast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WindDto {

    @SerializedName("speed")
    @Expose
    private float speed;
    @SerializedName("deg")
    @Expose
    private float direction;

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getDirection() {
        return direction;
    }

    public void setDirection(float direction) {
        this.direction = direction;
    }

}
