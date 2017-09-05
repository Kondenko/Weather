package com.aleksandersh.weather.utils;


/**
 * Created by Vladimir Kondenko on 22.07.17.
 */

public class Const {


    public static final String BASE_URL_WEATHER = "http://api.openweathermap.org/data/2.5/";
    public static final String BASE_URL_CITY = "http://api.geonames.org/";

    public static final String API_KEY_PARAM_WEATHER = "APPID";
    public static final String API_KEY_PARAM_CITY = "username";
    public static final String API_KEY_WEATHER = "55b2afa5241f9e7efe29e0c11fd124be";
    public static final String API_KEY_CITY = "vladimirkondenko"; // В сервисе используется имя аккаунта вместо API-ключа
//    public static final String API_KEY_CITY = "google";

    public static final String DI_API_SCOPE_WEATHER = "client_weather";
    public static final String DI_API_SCOPE_CITY = "client_city";

    public static final String OKHTTP_IDLING_RESOURCE_NAME = "OkHttp";

    public static final int DEFAULT_SUGGESTIONS_NUMBER = 10;

    public static final String DATABASE_NAME = "WeatherAppDatabase";

}
