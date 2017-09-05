package com.aleksandersh.weather.utils;


import android.support.annotation.DrawableRes;

import com.aleksandersh.weather.R;


/**
 * Created by AleksanderSh on 21.07.2017.
 * <p>
 * Позволяет подбирать иконки в зависимости от типа погоды.
 */

public class ConditionMapper {

    public static final String GROUP_STORM = "storm";

    public static final String GROUP_RAIN = "rain";

    public static final String GROUP_SNOW = "snow";

    public static final String GROUP_FOG = "fog";

    public static final String GROUP_CLEAR_SKY = "clear_sky";

    public static final String GROUP_CLOUDS = "clouds";

    /**
     * Получает ресурс drawable для отображения состояния погоды.
     *
     * @param group Группа состояний погоды.
     * @return Идентификатор Drawable ресурса. {@code 0}, если ресурс не определен.
     */
    @DrawableRes
    public static int getDrawableResourceByGroup(String group) {
        switch (group) {
            case GROUP_STORM:
                return R.drawable.ic_all_storm;
            case GROUP_RAIN:
                return R.drawable.ic_all_rain;
            case GROUP_SNOW:
                return R.drawable.ic_all_snow;
            case GROUP_FOG:
                return R.drawable.ic_all_fog;
            case GROUP_CLEAR_SKY:
                return R.drawable.ic_all_clear;
            case GROUP_CLOUDS:
                return R.drawable.ic_all_cloudy;
            default:
                return 0;
        }
    }

    public static String getGroupByServiceWeatherId(int weatherId) {
        if (200 <= weatherId && weatherId < 300) {
            return ConditionMapper.GROUP_STORM;
        } else if (300 <= weatherId && weatherId < 600) {
            return ConditionMapper.GROUP_RAIN;
        } else if (600 <= weatherId && weatherId < 700) {
            return ConditionMapper.GROUP_SNOW;
        } else if (700 <= weatherId && weatherId < 800) {
            return ConditionMapper.GROUP_FOG;
        } else if (weatherId == 800) {
            return ConditionMapper.GROUP_CLEAR_SKY;
        } else if (801 <= weatherId && weatherId < 900) {
            return ConditionMapper.GROUP_CLOUDS;
        } else {
            throw new IllegalArgumentException("Unknown weather condition - " + weatherId);
        }
    }

}
