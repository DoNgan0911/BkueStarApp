package com.example.bluestarapp;

public class Airport {
    private String airportID;
    private String airportName;
    private String place;

    public Airport(String airportID, String airportName, String place) {
        this.airportID = airportID;
        this.airportName = airportName;
        this.place = place;
    }

    public Airport() {
    }

    public String getAirportID() {
        return airportID;
    }

    public void setAirportID(String airportID) {
        this.airportID = airportID;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}

