package com.example.trip;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TripRequest(

        @NotBlank(message = "Destination is required")
        @Size(max = 100, message = "Destination must be 100 characters or fewer")
        String destination,

        @Size(max = 500, message = "Notes must be 500 characters or fewer")
        String notes

) {
}