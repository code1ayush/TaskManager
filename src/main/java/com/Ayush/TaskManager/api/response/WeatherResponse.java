package com.Ayush.TaskManager.api.response;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class WeatherResponse {

    public Current current;

    @Data
    public class Current{

        private int temperature;

        @JsonProperty("weather_descriptions")
        private ArrayList<String> weatherDescriptions;

        @JsonProperty("feelslike")
        private int feelsLike;
    }

}




