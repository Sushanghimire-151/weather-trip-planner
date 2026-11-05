package com.example.trip;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;

@Service
public class TripService {

    private final TripRepository repository;
    private final WeatherLocationClient locationClient;

    public TripService(
            TripRepository repository,
            WeatherLocationClient locationClient
    ) {
        this.repository = repository;
        this.locationClient = locationClient;
    }

    public List<TripResponse> getTrips() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private TripResponse toResponse(Trip trip) {
        return new TripResponse(
                trip.getId(),
                trip.getDestination(),
                trip.getNotes()
        );
    }

    public TripResponse createTrip(TripRequest request) {
        String destination = request.destination().trim();

        if (destination.length() < 2) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Destination must contain at least two characters"
            );
        }

        List<LocationLookupResponse> matches =
                locationClient.search(destination);

        if (matches.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "No matching locations were found"
            );
        }

        Trip trip = new Trip(
                destination,
                request.notes()
        );

        Trip savedTrip = repository.save(trip);

        return toResponse(savedTrip);
    }

    @Transactional
    public TripResponse updateTrip(Long id, TripRequest request) {
        Trip trip = repository.findById(id)
                .orElseThrow(() -> new TripNotFoundException(id));

        trip.updateDetails(
                request.destination().trim(),
                request.notes()
        );

        return toResponse(trip);
    }

    @Transactional
    public void deleteTrip(Long id) {
        Trip trip = repository.findById(id)
                .orElseThrow(() -> new TripNotFoundException(id));

        repository.delete(trip);
    }
}