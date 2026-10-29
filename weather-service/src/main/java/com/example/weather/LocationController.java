package com.example.weather;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    private final LocationService service;

    public LocationController(LocationService service) {
        this.service = service;
    }

    @GetMapping
    public List<LocationResponse> search(
            @RequestParam("name") String name
    ) {
        return service.search(name);
    }
}

/*
@RequestParam("name") reads this part of the URL:
/api/locations?name=Boston
                    ↑
              Method input

 Because the parameter is required by default, omitting name produces 400.
 */