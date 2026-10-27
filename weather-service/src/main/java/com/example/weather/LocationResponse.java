package com.example.weather;

public record LocationResponse(
        String name,
        String country,
        double latitude,
        double longitude
) {
}


/*
Why have two response types?
GeocodingResponse - Understand the provider’s response
LocationResponse - Define your application’s response

They currently have similar fields,
but separating them gives you control over your API if the provider’s structure changes.
 */