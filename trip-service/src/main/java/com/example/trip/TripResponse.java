// TripResponse is a DTO: it describes what your API sends to clients.

package com.example.trip;

public record TripResponse(
        Long id,
        String destination,
        String notes
) {
}