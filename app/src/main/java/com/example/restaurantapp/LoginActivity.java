package com.example.restaurantapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText usernameEdit, passwordEdit;
    private Button loginButton;
    private TextView forgotPassword, registerGuest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Find views
        usernameEdit = findViewById(R.id.username_edit);
        passwordEdit = findViewById(R.id.password_edit);
        loginButton = findViewById(R.id.login_button);
        forgotPassword = findViewById(R.id.forgot_password);
        registerGuest = findViewById(R.id.register_guest);

        // Login button click
        loginButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String username = usernameEdit.getText().toString();
				String password = passwordEdit.getText().toString();
				// TODO: Actual auth
				if (!username.isEmpty() && !password.isEmpty()) {
					Intent intent;
					if (username.toLowerCase().contains("staff")) {
						intent = new Intent(LoginActivity.this, StaffDashboardActivity.class);
					} else {
						intent = new Intent(LoginActivity.this, GuestDashboardActivity.class);
					}
					intent.putExtra("username", username);
					startActivity(intent);
					finish();
				} else {
					Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
				}
			}
		});

        // Forgot password click
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Navigate to forgot password screen
                Toast.makeText(LoginActivity.this, "Forgot Password clicked", Toast.LENGTH_SHORT).show();
            }
        });

        // Register as guest click
		// In onCreate, update this:
		registerGuest.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
			}
		});
    }
}