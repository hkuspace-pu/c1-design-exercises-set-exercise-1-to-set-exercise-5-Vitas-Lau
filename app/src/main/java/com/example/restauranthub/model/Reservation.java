package com.example.restauranthub.model;

public class Reservation {
    public String title;
    public String time;
    public String status;
    public String date;

    public Reservation(String title, String time, String status, String date) {
        this.title = title;
        this.time = time;
        this.status = status;
        this.date = date;
    }
}