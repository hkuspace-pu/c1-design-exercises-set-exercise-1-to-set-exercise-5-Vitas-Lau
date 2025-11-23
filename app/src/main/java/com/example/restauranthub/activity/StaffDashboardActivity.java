package com.example.restauranthub.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.cardview.widget.CardView;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;  // Import for logout button
import com.example.restauranthub.R;

public class StaffDashboardActivity extends AppCompatActivity {

    private CardView cardManageMenu, cardViewReservations, cardSettings;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_dashboard);

        // Find views
        cardManageMenu = findViewById(R.id.cardManageMenu);
        cardViewReservations = findViewById(R.id.cardViewReservations);
        cardSettings = findViewById(R.id.cardSettings);
        btnLogout = findViewById(R.id.btnLogout);

        // Set click listeners for navigation
        cardManageMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StaffDashboardActivity.this, MenuManageActivity.class);
                startActivity(intent);
            }
        });

        cardViewReservations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StaffDashboardActivity.this, StaffReservationsActivity.class);
                startActivity(intent);
            }
        });

        cardSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StaffDashboardActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        // Logout
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StaffDashboardActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}