package com.example.trip;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;

@Service
public class TripService {

    private final TripRepository repository;

    public TripService(TripRepository repository) {
        this.repository = repository;
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
        Trip trip = new Trip(
                request.destination().trim(),
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