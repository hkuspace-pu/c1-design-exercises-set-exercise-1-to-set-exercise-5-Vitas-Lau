package com.example.restauranthub.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.restauranthub.R;
import com.example.restauranthub.adapter.ReservationAdapter;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.List;

public class StaffReservationsActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private TabLayout tabReservations;
    private RecyclerView rvReservations;
    private ReservationAdapter adapter;
    private List<Reservation> reservations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_reservations);

        btnBack = findViewById(R.id.btnBack);
        tabReservations = findViewById(R.id.tabReservations);
        rvReservations = findViewById(R.id.rvReservations);

        rvReservations.setLayoutManager(new LinearLayoutManager(this));

        // Dummy data
        reservations = new ArrayList<>();
        reservations.add(new Reservation("John Smith", "18:30", 4, "+1 (555) 123-4567", "Wed, Nov 19, 2025"));
        reservations.add(new Reservation("Sarah Johnson", "19:00", 2, "+1 (555) 234-5678", "Wed, Nov 19, 2025"));
        reservations.add(new Reservation("Michael Brown", "20:00", 6, "+1 (555) 345-6789", "Wed, Nov 19, 2025"));
        reservations.add(new Reservation("Emily Davis", "18:00", 3, "+1 (555) 456-7890", "Thu, Nov 20, 2025"));
        reservations.add(new Reservation("Robert Wilson", "19:30", 5, "+1 (555) 567-8901", "Fri, Nov 21, 2025"));

        adapter = new ReservationAdapter(this, reservations); // Pass context
        rvReservations.setAdapter(adapter);

        // Setup Tabs for filtering
        tabReservations.addTab(tabReservations.newTab().setText("Today"));
        tabReservations.addTab(tabReservations.newTab().setText("Upcoming"));
        tabReservations.addTab(tabReservations.newTab().setText("Past"));

        tabReservations.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                filterReservations(tab.getText().toString());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        // Initial filter (e.g., Today)
        filterReservations("Today");

        // Back button
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void filterReservations(String tabName) {
        List<Reservation> filteredList = new ArrayList<>();
        // Simple filtering logic (adjust based on current date)
        // Assuming current date is Nov 19, 2025 for demo
        String currentDate = "Wed, Nov 19, 2025";

        for (Reservation res : reservations) {
            if (tabName.equals("Today") && res.date.equals(currentDate)) {
                filteredList.add(res);
            } else if (tabName.equals("Upcoming") && res.date.compareTo(currentDate) > 0) {
                filteredList.add(res);
            } else if (tabName.equals("Past") && res.date.compareTo(currentDate) < 0) {
                filteredList.add(res);
            }
        }
        adapter.updateReservations(filteredList);
    }

    // Simple POJO for reservation - fields made public
    public static class Reservation {
        public String guestName;
        public String time;
        public int guests;
        public String phone;
        public String date;

        public Reservation(String guestName, String time, int guests, String phone, String date) {
            this.guestName = guestName;
            this.time = time;
            this.guests = guests;
            this.phone = phone;
            this.date = date;
        }
    }
}