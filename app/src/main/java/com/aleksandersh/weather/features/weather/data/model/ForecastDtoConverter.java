package com.aleksandersh.weather.features.weather.data.model;


import com.aleksandersh.weather.features.weather.data.model.storable.Weather;
import com.aleksandersh.weather.features.weather.data.model.transferable.forecast.ForecastDto;
import com.aleksandersh.weather.features.weather.data.model.transferable.forecast.MainDto;
import com.aleksandersh.weather.features.weather.data.model.transferable.forecast.WeatherDto;

import java.util.List;

import static com.aleksandersh.weather.utils.ConditionMapper.getGroupByServiceWeatherId;


/**
 * Created by AleksanderSh on 15.07.2017.
 * <p>
 * Конвертирует объект передачи данных сервиса Open weather map в модель данных,
 * используемую в приложении.
 */

public class ForecastDtoConverter {

    public Weather convert(ForecastDto forecast) {
        Weather weather = new Weather();

        MainDto main = forecast.getMain();
        if (main != null) {
            weather.setTemperature(Math.round(main.getTemp()));
            weather.setPressure(main.getPressure());
            weather.setHumidity(main.getHumidity());
        }

        com.aleksandersh.weather.features.weather.data.model.transferable.forecast.CloudsDto cloudsDto = forecast.getCloudsDto();
        if (cloudsDto != null) {
            weather.setCloudiness(cloudsDto.getCloudiness());
        }

        com.aleksandersh.weather.features.weather.data.model.transferable.forecast.WindDto windDto = forecast.getWind();
        if (windDto != null) {
            weather.setWindSpeed(windDto.getSpeed());
            weather.setWindDirection(windDto.getDirection());
        }

        String group = null;
        List<WeatherDto> weatherConditionDtoList = forecast.getWeather();
        if (weatherConditionDtoList != null && !weatherConditionDtoList.isEmpty()) {
            group = getGroupByServiceWeatherId(weatherConditionDtoList.get(0).getId());
        }

        weather.setGroup(group != null ? group : "");

        weather.setTimestamp(forecast.getDt());
        weather.setDateReadable(forecast.getDtTxt());

        return weather;
    }

}
