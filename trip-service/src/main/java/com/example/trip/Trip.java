package com.example.trip;

import jakarta.persistence.*;

@Entity
@Table(name = "trips")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String destination;

    @Column(length = 500)
    private String notes;

    protected Trip() {
    }

    public Trip(String destination, String notes) {
        this.destination = destination;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public String getDestination() {
        return destination;
    }

    public String getNotes() {
        return notes;
    }

    public void updateDetails(String destination, String notes) {
        this.destination = destination;
        this.notes = notes;
    }
}