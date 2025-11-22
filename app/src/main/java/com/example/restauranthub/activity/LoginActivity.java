package com.example.restauranthub.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.restauranthub.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // TODO: Add click listeners later
        binding.tvForgotPassword.setOnClickListener(v -> {
            // Navigate to ForgotPasswordFragment
        });

        binding.tvRegisterGuest.setOnClickListener(v -> {
            // Navigate to GuestRegistrationFragment
        });

        binding.btnLogin.setOnClickListener(v -> {
            // Handle login (staff or guest)
        });
    }
}