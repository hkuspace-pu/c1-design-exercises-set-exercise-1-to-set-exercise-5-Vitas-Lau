package com.example.restaurantapp.model;

import java.util.Date;

public class Reservation {
    private String id;
    private String guestName;
    private Date dateTime;
    private int partySize;
    private String phone;
    private String status; // "today", "upcoming", "past"

    public Reservation(String id, String guestName, Date dateTime, int partySize, String phone, String status) {
        this.id = id;
        this.guestName = guestName;
        this.dateTime = dateTime;
        this.partySize = partySize;
        this.phone = phone;
        this.status = status;
    }

    // Getters
    public String getId() { return id; }
    public String getGuestName() { return guestName; }
    public Date getDateTime() { return dateTime; }
    public int getPartySize() { return partySize; }
    public String getPhone() { return phone; }
    public String getStatus() { return status; }
}