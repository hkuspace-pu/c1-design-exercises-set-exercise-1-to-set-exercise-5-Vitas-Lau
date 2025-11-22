package com.example.restauranthub.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.cardview.widget.CardView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.restauranthub.R;

public class GuestDashboardActivity extends AppCompatActivity {

    private CardView cardBrowseMenu, cardBookTable, cardMyReservations, cardMyProfile;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_dashboard);

        // Find views
        cardBrowseMenu = findViewById(R.id.cardBrowseMenu);
        cardBookTable = findViewById(R.id.cardBookTable);
        cardMyReservations = findViewById(R.id.cardMyReservations);
        cardMyProfile = findViewById(R.id.cardMyProfile);
        btnLogout = findViewById(R.id.btnLogout);

        // Set click listener for Logout
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuestDashboardActivity.this, LoginActivity.class);
                startActivity(intent);
                finish(); // Close dashboard
            }
        });

        // Set click listeners for navigation
        cardBrowseMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuestDashboardActivity.this, MenuBrowseActivity.class);
                startActivity(intent);
            }
        });

        cardBookTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuestDashboardActivity.this, BookTableActivity.class);
                startActivity(intent);
            }
        });

        cardMyReservations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuestDashboardActivity.this, MyReservationsActivity.class);
                startActivity(intent);
            }
        });

        cardMyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuestDashboardActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}