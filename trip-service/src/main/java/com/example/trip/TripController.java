package com.example.trip;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    private final TripService service;

    public TripController(TripService service) {
        this.service = service;
    }

    @GetMapping
    public List<TripResponse> getTrips() {
        return service.getTrips();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TripResponse createTrip(@Valid @RequestBody TripRequest request) {
        return service.createTrip(request);
    }

    @PutMapping("/{id}")
    public TripResponse updateTrip(
            @PathVariable("id") Long id,
            @Valid @RequestBody TripRequest request
    ) {
        return service.updateTrip(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrip(@PathVariable("id") Long id) {
        service.deleteTrip(id);
    }
}