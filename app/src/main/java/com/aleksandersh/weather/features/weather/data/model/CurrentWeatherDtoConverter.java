package com.aleksandersh.weather.features.weather.data.model;


import com.aleksandersh.weather.features.weather.data.model.storable.Weather;
import com.aleksandersh.weather.features.weather.data.model.transferable.current.CloudsDto;
import com.aleksandersh.weather.features.weather.data.model.transferable.current.CurrentWeatherDto;
import com.aleksandersh.weather.features.weather.data.model.transferable.current.GeneralDto;
import com.aleksandersh.weather.features.weather.data.model.transferable.current.WeatherConditionDto;
import com.aleksandersh.weather.features.weather.data.model.transferable.current.WindDto;

import java.util.List;

import static com.aleksandersh.weather.utils.ConditionMapper.getGroupByServiceWeatherId;


/**
 * Created by AleksanderSh on 15.07.2017.
 * <p>
 * Конвертирует объект передачи данных сервиса Open weather map в модель данных,
 * используемую в приложении.
 */

public class CurrentWeatherDtoConverter {

    public Weather convert(CurrentWeatherDto dto) {
        Weather weather = new Weather();

        GeneralDto generalDto = dto.getGeneral();
        if (generalDto != null) {
            weather.setTemperature(generalDto.getTemperature());
            weather.setPressure(generalDto.getPressure());
            weather.setHumidity(generalDto.getHumidity());
        }

        CloudsDto cloudsDto = dto.getClouds();
        if (cloudsDto != null) {
            weather.setCloudiness(cloudsDto.getCloudiness());
        }

        WindDto windDto = dto.getWind();
        if (windDto != null) {
            weather.setWindSpeed(windDto.getSpeed());
            weather.setWindDirection(windDto.getDirection());
        }

        String description;
        String group = null;
        List<WeatherConditionDto> weatherConditionDtoList = dto.getWeatherConditions();
        if (weatherConditionDtoList != null && !weatherConditionDtoList.isEmpty()) {
            try {
                group = getGroupByServiceWeatherId(weatherConditionDtoList.get(0).getId());
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            description = getDescriptionFromWeatherConditions(weatherConditionDtoList);
        } else {
            description = "";
            group = "";
        }

        weather.setDescription(description);
        weather.setGroup(group != null ? group : "");

        return weather;
    }

    private String getDescriptionFromWeatherConditions(List<WeatherConditionDto> conditions) {
        String description;

        if (conditions.size() == 1) {
            description = conditions.get(0).getDescription();
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            for (WeatherConditionDto conditionDto : conditions) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append(", ");
                }
                stringBuilder.append(conditionDto.getDescription());
            }
            description = stringBuilder.toString();
        }

        return description.substring(0, 1).toUpperCase().concat(description.substring(1));
    }

}
