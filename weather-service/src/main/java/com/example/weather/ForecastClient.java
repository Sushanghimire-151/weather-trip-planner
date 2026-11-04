package com.example.weather;

import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ForecastClient {

    private final RestClient client;

    public ForecastClient() {
        SimpleClientHttpRequestFactory factory =
                new SimpleClientHttpRequestFactory();

        factory.setConnectTimeout(3000);
        factory.setReadTimeout(5000);

        this.client = RestClient.builder()
                .baseUrl("https://api.open-meteo.com")
                .requestFactory(factory)
                .build();
    }

    public ForecastApiResponse getForecast(
            double latitude,
            double longitude
    ) {
        return client.get()
                .uri(builder -> builder
                        .path("/v1/forecast")
                        .queryParam("latitude", latitude)
                        .queryParam("longitude", longitude)
                        .queryParam(
                                "daily",
                                "temperature_2m_max,"
                                        + "temperature_2m_min,"
                                        + "precipitation_probability_max"
                        )
                        .queryParam("temperature_unit", "celsius")
                        .queryParam("timezone", "auto")
                        .queryParam("forecast_days", 3)
                        .build())
                .retrieve()
                .body(ForecastApiResponse.class);
    }
}