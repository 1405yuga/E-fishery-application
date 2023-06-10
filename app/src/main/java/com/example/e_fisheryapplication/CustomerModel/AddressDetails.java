package com.example.e_fisheryapplication.CustomerModel;

public class AddressDetails {
    String faltno,area,landmark,city;
    int pin;

    public AddressDetails() {
    }

    public AddressDetails(String faltno, String area, String landmark, String city, int pin) {
        this.faltno = faltno;
        this.area = area;
        this.landmark = landmark;
        this.city = city;
        this.pin = pin;
    }

    public String getFaltno() {
        return faltno;
    }

    public void setFaltno(String faltno) {
        this.faltno = faltno;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }
}
