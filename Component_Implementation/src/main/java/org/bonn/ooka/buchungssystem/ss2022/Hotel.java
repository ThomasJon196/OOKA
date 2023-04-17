package org.bonn.ooka.buchungssystem.ss2022;

public class Hotel {
    private String name;
    private String address;
    private int rating;


    public Hotel(String name, String address, int rating) {
        this.name = name;
        this.address = address;
        this.rating = rating;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String toString(){
        return "Name: " + getName() + ", Address: " + getAddress() + ", Rating: " + getRating();
    }
}