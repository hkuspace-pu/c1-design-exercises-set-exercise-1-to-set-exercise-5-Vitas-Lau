package com.example.restaurantapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class StaffDashboardActivity extends AppCompatActivity {

    private TextView greetingText;
    private Button logoutButton;
    private CardView manageMenuCard, viewReservationsCard, settingsCard;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_dashboard);

        // Get username from intent
        username = getIntent().getStringExtra("username");

        // Find views
        greetingText = findViewById(R.id.greeting_text);
        logoutButton = findViewById(R.id.logout_button);
        manageMenuCard = findViewById(R.id.manage_menu_card);
        viewReservationsCard = findViewById(R.id.view_reservations_card);
        settingsCard = findViewById(R.id.settings_card);

        // Set greeting
        greetingText.setText(String.format(getString(R.string.greeting), username));

        // Logout button click
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Clear session/auth if needed
                startActivity(new Intent(StaffDashboardActivity.this, LoginActivity.class));
                finish();
            }
        });

        // Card clicks (placeholders - replace with actual navigation)
        manageMenuCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Navigate to menu management activity
                Toast.makeText(StaffDashboardActivity.this, "Manage Menu clicked", Toast.LENGTH_SHORT).show();
            }
        });

        viewReservationsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Navigate to reservations view activity
                Toast.makeText(StaffDashboardActivity.this, "View Reservations clicked", Toast.LENGTH_SHORT).show();
            }
        });

        settingsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Navigate to settings activity
                Toast.makeText(StaffDashboardActivity.this, "Settings clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}