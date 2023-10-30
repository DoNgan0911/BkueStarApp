package com.example.bluestarapp;

public class Flight {
    private String flyID;
    private String plID;
    private String airportID;
    private String fromLocation;
    private String toLocation;
    private String departureTime;
    private String arrivalTime;
    private String departureDay;
    private String originalPrice;

    public Flight() {

    }

    public Flight(String flyID, String plID, String airportID, String fromLocation, String toLocation, String departureTime, String arrivalTime, String departureDay, String originalPrice) {
        this.flyID = flyID;
        this.plID = plID;
        this.airportID = airportID;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.departureDay = departureDay;
        this.originalPrice = originalPrice;
    }

    public String getFlyID() {
        return flyID;
    }

    public void setFlyID(String flyID) {
        this.flyID = flyID;
    }

    public String getPlID() {
        return plID;
    }

    public void setPlID(String plID) {
        this.plID = plID;
    }

    public String getAirportID() {
        return airportID;
    }

    public void setAirportID(String airportID) {
        this.airportID = airportID;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public String getToLocation() {
        return toLocation;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDepartureDay() {
        return departureDay;
    }

    public void setDepartureDay(String departureDay) {
        this.departureDay = departureDay;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }
}
