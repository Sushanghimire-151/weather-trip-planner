package com.example.trip;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LocationLookupResponse(
        String name,
        String country,
        double latitude,
        double longitude
) {
}