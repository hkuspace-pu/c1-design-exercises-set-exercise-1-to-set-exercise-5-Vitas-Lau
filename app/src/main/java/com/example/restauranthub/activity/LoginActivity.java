package com.example.restauranthub.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.restauranthub.R;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnLogin;

    private TextView tvRegisterGuest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegisterGuest = findViewById(R.id.tvRegisterGuest);

        // Set click listener for "Register as Guest"
        tvRegisterGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (username.equals("staff") && password.equals("staff")) {
                    // Navigate to Staff Home
                    Intent intent = new Intent(LoginActivity.this, StaffDashboardActivity.class);
                    startActivity(intent);
                    finish(); // Optional: Close login activity
                } else if (username.equals("guest") && password.equals("guest")) {
                    // Navigate to Guest Home
                    Intent intent = new Intent(LoginActivity.this, GuestDashboardActivity.class);
                    startActivity(intent);
                    finish(); // Optional: Close login activity
                } else {
                    // Invalid credentials
                    Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}