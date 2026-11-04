package com.example.weather;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ForecastApiResponse(
        String timezone,
        Daily daily
) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Daily(
            List<String> time,

            @JsonProperty("temperature_2m_max")
            List<Double> maxTemperatures,

            @JsonProperty("temperature_2m_min")
            List<Double> minTemperatures,

            @JsonProperty("precipitation_probability_max")
            List<Integer> precipitationProbabilities
    ) {
    }
}