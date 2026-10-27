package com.example.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GeocodingResponse(List<Location> results) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Location(
            String name,
            String country,
            double latitude,
            double longitude
    ) {

    }

}



/*
What to understand:
- Open-Meteo returns an object containing a results array.
- List<Location> represents that array.
- The nested Location record represents each result.
- @JsonIgnoreProperties lets us ignore provider fields we don’t use.
 */