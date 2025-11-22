package com.example.restauranthub.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.cardview.widget.CardView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.restauranthub.R;

public class StaffDashboardActivity extends AppCompatActivity {

    private CardView cardManageMenu, cardViewReservations, cardSettings;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_dashboard);

        // Find views with null checks to prevent crashes
        cardManageMenu = findViewById(R.id.cardManageMenu);
        cardViewReservations = findViewById(R.id.cardViewReservations);
        cardSettings = findViewById(R.id.cardSettings);
        btnLogout = findViewById(R.id.btnLogout);

        if (cardManageMenu != null) {
            cardManageMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(StaffDashboardActivity.this, MenuManageActivity.class);
                    startActivity(intent);
                }
            });
        }

        if (cardViewReservations != null) {
            cardViewReservations.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(StaffDashboardActivity.this, StaffReservationsActivity.class);
                    startActivity(intent);
                }
            });
        }

        if (cardSettings != null) {
            cardSettings.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(StaffDashboardActivity.this, NotificationSettingsActivity.class);
                    startActivity(intent);
                }
            });
        }

        if (btnLogout != null) {
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
}