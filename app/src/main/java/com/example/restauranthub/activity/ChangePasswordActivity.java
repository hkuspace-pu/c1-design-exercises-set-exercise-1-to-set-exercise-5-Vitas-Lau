package com.example.restauranthub.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.restauranthub.R;

public class ChangePasswordActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private EditText etCurrentPassword, etNewPassword, etConfirmPassword;
    private ImageButton btnToggleCurrent, btnToggleNew, btnToggleConfirm;
    private Button btnCancel, btnChangePassword;

    private boolean isCurrentVisible = false, isNewVisible = false, isConfirmVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        btnBack = findViewById(R.id.btnBack);
        etCurrentPassword = findViewById(R.id.etCurrentPassword);
        etNewPassword = findViewById(R.id.etNewPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnToggleCurrent = findViewById(R.id.btnToggleCurrentPassword);
        btnToggleNew = findViewById(R.id.btnToggleNewPassword);
        btnToggleConfirm = findViewById(R.id.btnToggleConfirmPassword);
        btnCancel = findViewById(R.id.btnCancel);
        btnChangePassword = findViewById(R.id.btnChangePassword);

        // Toggle visibility listeners
        btnToggleCurrent.setOnClickListener(v -> togglePassword(etCurrentPassword, btnToggleCurrent, isCurrentVisible = !isCurrentVisible));
        btnToggleNew.setOnClickListener(v -> togglePassword(etNewPassword, btnToggleNew, isNewVisible = !isNewVisible));
        btnToggleConfirm.setOnClickListener(v -> togglePassword(etConfirmPassword, btnToggleConfirm, isConfirmVisible = !isConfirmVisible));

        // Back button
        btnBack.setOnClickListener(v -> finish());

        // Cancel button
        btnCancel.setOnClickListener(v -> finish());

        // Change Password button
        btnChangePassword.setOnClickListener(v -> {
            String current = etCurrentPassword.getText().toString().trim();
            String newPass = etNewPassword.getText().toString().trim();
            String confirm = etConfirmPassword.getText().toString().trim();

            if (current.isEmpty() || newPass.isEmpty() || confirm.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else if (!newPass.equals(confirm)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            } else if (newPass.length() < 8) {
                Toast.makeText(this, "Password must be at least 8 characters", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Password changed successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void togglePassword(EditText editText, ImageButton toggleBtn, boolean isVisible) {
        editText.setTransformationMethod(isVisible ? null : PasswordTransformationMethod.getInstance());
        toggleBtn.setImageResource(isVisible ? R.drawable.ic_visibility_off : R.drawable.ic_visibility);
        editText.setSelection(editText.getText().length()); // Cursor to end
    }
}