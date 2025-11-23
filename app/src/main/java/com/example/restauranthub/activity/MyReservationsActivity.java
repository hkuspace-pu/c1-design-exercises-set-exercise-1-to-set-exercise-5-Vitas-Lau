package com.example.restauranthub.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;   // Added missing import
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.restauranthub.R;
import com.example.restauranthub.adapter.GuestReservationAdapter;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.List;

public class MyReservationsActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private TabLayout tabReservations;
    private RecyclerView rvReservations;
    private View cardEmptyState;          // Changed to View (contains tvEmptyTitle)
    private Button btnBookTableEmpty;
    private TextView tvEmptyTitle;        // Added field for clean access
    private GuestReservationAdapter adapter;
    private List<Reservation> allReservations = new ArrayList<>();
    private List<Reservation> upcomingReservations = new ArrayList<>();
    private List<Reservation> pastReservations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reservations);

        btnBack = findViewById(R.id.btnBack);
        tabReservations = findViewById(R.id.tabReservations);
        rvReservations = findViewById(R.id.rvReservations);
        cardEmptyState = findViewById(R.id.cardEmptyState);
        tvEmptyTitle = findViewById(R.id.tvEmptyTitle);   // Find once
        btnBookTableEmpty = findViewById(R.id.btnBookTableEmpty);

        rvReservations.setLayoutManager(new LinearLayoutManager(this));

        // Dummy data
        allReservations.add(new Reservation("Table for 4", "18:30", "Confirmed", "2025-11-25"));
        allReservations.add(new Reservation("Table for 2", "19:00", "Confirmed", "2025-11-26"));
        allReservations.add(new Reservation("Table for 6", "20:00", "Confirmed", "2025-11-20")); // Past

        // Filter
        upcomingReservations = filterUpcoming();
        pastReservations = filterPast();

        adapter = new GuestReservationAdapter(this, upcomingReservations);
        rvReservations.setAdapter(adapter);

        updateEmptyState(upcomingReservations.isEmpty());

        // Tabs
        tabReservations.addTab(tabReservations.newTab().setText("Upcoming"));
        tabReservations.addTab(tabReservations.newTab().setText("Past"));

        tabReservations.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    adapter.updateReservations(upcomingReservations);
                    updateEmptyState(upcomingReservations.isEmpty());
                } else {
                    adapter.updateReservations(pastReservations);
                    updateEmptyState(pastReservations.isEmpty());
                }
            }

            @Override public void onTabUnselected(TabLayout.Tab tab) {}
            @Override public void onTabReselected(TabLayout.Tab tab) {}
        });

        // Book a Table button in empty state
        btnBookTableEmpty.setOnClickListener(v -> {
            startActivity(new Intent(this, BookTableActivity.class));
        });

        // Back button
        btnBack.setOnClickListener(v -> finish());
    }

    private List<Reservation> filterUpcoming() {
        List<Reservation> list = new ArrayList<>();
        for (Reservation r : allReservations) {
            if (r.date.compareTo("2025-11-23") >= 0) { // Today or future
                list.add(r);
            }
        }
        return list;
    }

    private List<Reservation> filterPast() {
        List<Reservation> list = new ArrayList<>();
        for (Reservation r : allReservations) {
            if (r.date.compareTo("2025-11-23") < 0) {
                list.add(r);
            }
        }
        return list;
    }

    private void updateEmptyState(boolean isEmpty) {
        if (isEmpty) {
            rvReservations.setVisibility(View.GONE);
            cardEmptyState.setVisibility(View.VISIBLE);
            tvEmptyTitle.setText(tabReservations.getSelectedTabPosition() == 0 ?
                    "No Upcoming Reservations" : "No Past Reservations");
        } else {
            rvReservations.setVisibility(View.VISIBLE);
            cardEmptyState.setVisibility(View.GONE);
        }
    }

    // Reservation model - fields are now public
    public static class Reservation {
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
}