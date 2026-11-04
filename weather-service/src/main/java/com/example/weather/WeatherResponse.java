package com.example.weather;

import java.util.List;

public record WeatherResponse(
        double requestedLatitude,
        double requestedLongitude,
        String timezone,
        String temperatureUnit,
        String precipitationProbabilityUnit,
        List<DailyForecast> days
) {

    public record DailyForecast(
            String date,
            Double minTemperature,
            Double maxTemperature,
            Integer maxPrecipitationProbability
    ) {
    }
}