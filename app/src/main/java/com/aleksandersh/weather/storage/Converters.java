package com.aleksandersh.weather.storage;


import android.arch.persistence.room.TypeConverter;

import java.util.Date;


/**
 * Created by Vladimir Kondenko on 11.08.17.
 *
 * Helps Room convert classes to types that can be saved in the database
 * and vice versa.
 */

public class Converters {

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

}
