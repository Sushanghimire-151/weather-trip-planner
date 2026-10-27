package com.example.weather;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.server.ResponseStatusException;

@Service
public class LocationService {

    private final GeocodingClient client;

    public LocationService(GeocodingClient client) {
        this.client = client;
    }

    public List<LocationResponse> search(String name) {
        if (name == null || name.trim().length() < 2) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Enter a city name with at least two characters"
            );
        }

        try {
            GeocodingResponse response = client.search(name.trim());

            if (response == null) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_GATEWAY,
                        "Location provider returned an empty response"
                );
            }

            if (response.results() == null) {
                return List.of();
            }

            return response.results()
                    .stream()
                    .map(location -> new LocationResponse(
                            location.name(),
                            location.country(),
                            location.latitude(),
                            location.longitude()
                    ))
                    .toList();

        } catch (RestClientException exception) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_GATEWAY,
                    "Unable to retrieve locations from the provider",
                    exception
            );
        }
    }
}

/*
Input has fewer than two non-padding characters	 400 Bad Request
Provider finds matching locations	             200 OK, with results
Provider finds no locations	                     200 OK, with []
Provider request fails	                         502 Bad Gateway
 */