package com.example.trip;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.server.ResponseStatusException;

@Component
public class WeatherLocationClient {

    private final RestClient client;

    public WeatherLocationClient(
            @Value("${weather.service.base-url}") String baseUrl
    ) {
        SimpleClientHttpRequestFactory factory =
                new SimpleClientHttpRequestFactory();

        factory.setConnectTimeout(3000);
        factory.setReadTimeout(10000);

        this.client = RestClient.builder()
                .baseUrl(baseUrl)
                .requestFactory(factory)
                .build();
    }

    public List<LocationLookupResponse> search(String name) {
        try {
            LocationLookupResponse[] locations = client.get()
                    .uri(builder -> builder
                            .path("/api/locations")
                            .queryParam("name", "{name}")
                            .build(name))
                    .retrieve()
                    .body(LocationLookupResponse[].class);

            if (locations == null) {
                throw new ResponseStatusException(
                        HttpStatus.SERVICE_UNAVAILABLE,
                        "Location lookup returned an empty response"
                );
            }

            return Arrays.asList(locations);

        } catch (RestClientException exception) {
            throw new ResponseStatusException(
                    HttpStatus.SERVICE_UNAVAILABLE,
                    "Location lookup is unavailable. Please try again.",
                    exception
            );
        }
    }
}