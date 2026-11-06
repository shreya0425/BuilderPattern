package com.Builder.builderOne;

import java.time.LocalDateTime;

public final class Segment {
    private final String origin;
    private final String destination;
    private final LocalDateTime departure;
    private final LocalDateTime arrival;

    public Segment(String origin,
                   String destination,
                   LocalDateTime departure,
                   LocalDateTime arrival){
        if (origin == null || !origin.matches("[A-Z]{3}")) {
            throw new IllegalArgumentException("Invalid origin");
        }

        if (destination == null || !destination.matches("[A-Z]{3}")) {
            throw new IllegalArgumentException("Invalid destination");
        }

        if (origin.equals(destination)) {
            throw new IllegalArgumentException(
                    "Origin and destination cannot be the same"
            );
        }

        if (departure == null || arrival == null) {
            throw new IllegalArgumentException(
                    "Departure and arrival are required"
            );
        }

        if (arrival.isBefore(departure)) {
            throw new IllegalArgumentException(
                    "Arrival cannot be before departure"
            );
        }

        this.origin = origin;
        this.destination = destination;
        this.departure = departure;
        this.arrival = arrival;
    }


}
