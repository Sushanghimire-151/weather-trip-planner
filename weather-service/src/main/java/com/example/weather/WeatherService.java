package com.example.weather;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.server.ResponseStatusException;

@Service
public class WeatherService {

    private final ForecastClient client;

    public WeatherService(ForecastClient client) {
        this.client = client;
    }

    public WeatherResponse getForecast(
            double latitude,
            double longitude
    ) {
        if (!Double.isFinite(latitude)
                || latitude < -90
                || latitude > 90) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Latitude must be between -90 and 90"
            );
        }

        if (!Double.isFinite(longitude)
                || longitude < -180
                || longitude > 180) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Longitude must be between -180 and 180"
            );
        }

        ForecastApiResponse response;

        try {
            response = client.getForecast(latitude, longitude);
        } catch (RestClientException exception) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_GATEWAY,
                    "Unable to retrieve the forecast",
                    exception
            );
        }

        if (response == null || response.daily() == null) {
            throw invalidProviderResponse();
        }

        ForecastApiResponse.Daily daily = response.daily();

        if (daily.time() == null
                || daily.maxTemperatures() == null
                || daily.minTemperatures() == null
                || daily.precipitationProbabilities() == null) {
            throw invalidProviderResponse();
        }

        int count = daily.time().size();

        if (count == 0
                || daily.maxTemperatures().size() != count
                || daily.minTemperatures().size() != count
                || daily.precipitationProbabilities().size() != count) {
            throw invalidProviderResponse();
        }

        List<WeatherResponse.DailyForecast> days = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            days.add(new WeatherResponse.DailyForecast(
                    daily.time().get(i),
                    daily.minTemperatures().get(i),
                    daily.maxTemperatures().get(i),
                    daily.precipitationProbabilities().get(i)
            ));
        }

        return new WeatherResponse(
                latitude,
                longitude,
                response.timezone(),
                "°C",
                "%",
                days
        );
    }

    private ResponseStatusException invalidProviderResponse() {
        return new ResponseStatusException(
                HttpStatus.BAD_GATEWAY,
                "Forecast provider returned incomplete data"
        );
    }
}