package com.example.uiprototype.model;

public class Locker {
    private final int id;
    private final String size;
    private final String location;
    private boolean available;

    private String rentedBy;
    public Locker(int id, String size, String location, boolean available) {
        this.id = id;
        this.size = size;
        this.location = location;
        this.available = available;
        this.rentedBy = null;
    }

    public int getId() { return id; }
    public String getSize() { return size; }
    public String getLocation() { return location; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    public String getRentedBy(){
        return rentedBy;

    }
    public void setRentedBy(String rentedBy) {
        this.rentedBy = rentedBy;
    }


}
