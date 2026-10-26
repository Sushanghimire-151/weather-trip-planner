package com.example.trip;

public class TripNotFoundException extends RuntimeException {

    public TripNotFoundException(Long id) {
        super("Trip " + id + " was not found");
    }
}