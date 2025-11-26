package com.example.restauranthub.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.restauranthub.model.Reservation;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MyReservationsViewModel extends ViewModel {

    private final MutableLiveData<List<Reservation>> upcomingReservations = new MutableLiveData<>();
    private final MutableLiveData<List<Reservation>> pastReservations = new MutableLiveData<>();

    private List<Reservation> allReservations = new ArrayList<>();

    public LiveData<List<Reservation>> getUpcomingReservations() {
        return upcomingReservations;
    }

    public LiveData<List<Reservation>> getPastReservations() {
        return pastReservations;
    }

    public void loadReservations() {
        // Dummy data creation
        allReservations.add(new Reservation("Table for 4", "18:30", "Confirmed", "2025-11-25"));
        allReservations.add(new Reservation("Table for 2", "19:00", "Confirmed", "2025-11-20")); // Past

        filterReservations();
    }

    private void filterReservations() {
        // In a real app, you'd use a more robust date comparison
        String today = "2025-11-23";

        List<Reservation> upcoming = allReservations.stream()
                .filter(r -> r.date.compareTo(today) >= 0)
                .collect(Collectors.toList());
        upcomingReservations.setValue(upcoming);

        List<Reservation> past = allReservations.stream()
                .filter(r -> r.date.compareTo(today) < 0)
                .collect(Collectors.toList());
        pastReservations.setValue(past);
    }
}