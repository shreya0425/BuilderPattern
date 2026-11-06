package com.Builder.builderOne;


import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Itinerary {
    private final String travelerName;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String origin;
    private final String destination;

    private final List<Segment> segments;
    private final List<String> labels;
    private final int budget;
    private final boolean travelInsurance;

    private Itinerary(Builder b){
        this.travelerName = b.travelerName;
        this.startDate = b.startDate;
        this.endDate = b.endDate;
        this.origin = b.origin;
        this.destination = b.destination;


        this.segments = List.copyOf(b.segments);
        this.labels = List.copyOf(b.labels);
        this.budget = b.budget;
        this.travelInsurance = b.travelInsurance;
    }
    public static class Builder{
        private String travelerName;
        private LocalDate startDate;
        private LocalDate endDate;
        private String origin;
        private String destination;
        private List<Segment> segments = new ArrayList<>();
        private List<String> labels = new ArrayList<>();
        private int budget = 0;
        private boolean travelInsurance = false;

        public Builder travelerName(String travelerName){
            this.travelerName= travelerName;
            return this;
        }

        public Builder startDate(LocalDate startDate){
            this.startDate = startDate;
            return this;
        }

        public Builder endDate(LocalDate endDate){
            this.endDate = endDate;
            return this;
        }

        public Builder origin(String origin){
            this.origin = origin;
            return this;
        }

        public Builder destination(String destination){
            this.destination = destination;
            return this;
        }

        public Builder budget(int budget) {
            this.budget = budget;
            return this;
        }

        public Builder travelInsurance(boolean travelInsurance) {
            this.travelInsurance = travelInsurance;
            return this;
        }

        public Builder addSegment(Segment segment){
            this.segments.add(Objects.requireNonNull(segment, "Segment cannot be null"));
            return this;
        }

        public Builder addLabel(String label){

            if (label == null || label.isBlank()) {
                throw new IllegalArgumentException(
                        "Label cannot be null or blank"
                );
            }
            this.labels.add(label);
            return this;
        }

        // Replace all segments
        public Builder segments(List<Segment> segments) {
            Objects.requireNonNull(segments, "Segments cannot be null");

            this.segments = new ArrayList<>();

            for (Segment segment : segments) {
                addSegment(segment);
            }

            return this;
        }

        // Replace all labels
        public Builder labels(List<String> labels) {
            Objects.requireNonNull(labels, "Labels cannot be null");

            this.labels = new ArrayList<>();

            for (String label : labels) {
                addLabel(label);
            }

            return this;
        }

        public Itinerary build(){
            if (travelerName == null || travelerName.isBlank()) {
                throw new IllegalArgumentException("Traveler name is required");
            }

            if (startDate == null || endDate == null || endDate.isBefore(startDate)) {
                throw new IllegalArgumentException("Dates are required");
            }

            if (origin == null || !origin.matches("[A-Z]{3}")) {
                throw new IllegalArgumentException(
                        "Origin must be a 3-letter uppercase IATA code"
                );
            }

            if (destination == null || !destination.matches("[A-Z]{3}")) {
                throw new IllegalArgumentException(
                        "Destination must be a 3-letter uppercase IATA code"
                );
            }

            if (origin.equals(destination)) {
                throw new IllegalArgumentException(
                        "Origin and destination cannot be the same"
                );
            }
            
            if (budget < 0) {
                throw new IllegalArgumentException(
                        "Budget cannot be negative"
                );
            }
            return new Itinerary(this);
        }
    }

}
