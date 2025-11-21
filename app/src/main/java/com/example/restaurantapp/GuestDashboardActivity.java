package com.example.restaurantapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class GuestDashboardActivity extends AppCompatActivity {

    private TextView greetingText;
    private Button logoutButton;
    private CardView browseMenuCard, bookTableCard, myReservationsCard, myProfileCard, notificationsCard;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_dashboard);

        // Get username from intent
        username = getIntent().getStringExtra("username");

        // Find views
        greetingText = findViewById(R.id.greeting_text);
        logoutButton = findViewById(R.id.logout_button);
        browseMenuCard = findViewById(R.id.browse_menu_card);
        bookTableCard = findViewById(R.id.book_table_card);
        myReservationsCard = findViewById(R.id.my_reservations_card);
        myProfileCard = findViewById(R.id.my_profile_card);
        notificationsCard = findViewById(R.id.notifications_card);

        // Set greeting
        greetingText.setText(String.format(getString(R.string.greeting), username));

        // Logout button click
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Clear session/auth if needed
                startActivity(new Intent(GuestDashboardActivity.this, LoginActivity.class));
                finish();
            }
        });

        // Card clicks (placeholders - replace with actual navigation)
        browseMenuCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Navigate to menu browsing activity
                Toast.makeText(GuestDashboardActivity.this, "Browse Menu clicked", Toast.LENGTH_SHORT).show();
            }
        });

        bookTableCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Navigate to booking activity
                Toast.makeText(GuestDashboardActivity.this, "Book a Table clicked", Toast.LENGTH_SHORT).show();
            }
        });

        myReservationsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Navigate to reservations list activity
                Toast.makeText(GuestDashboardActivity.this, "My Reservations clicked", Toast.LENGTH_SHORT).show();
            }
        });

        myProfileCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Navigate to profile editing activity
                Toast.makeText(GuestDashboardActivity.this, "My Profile clicked", Toast.LENGTH_SHORT).show();
            }
        });

        notificationsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Navigate to notifications activity
                Toast.makeText(GuestDashboardActivity.this, "Notifications clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}