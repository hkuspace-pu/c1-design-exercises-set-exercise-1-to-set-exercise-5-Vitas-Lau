package com.example.restauranthub.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import com.example.restauranthub.R;

public class GuestProfileActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private ImageView ivProfilePhoto;
    private TextView tvChangePhoto;
    private EditText etFullName, etEmail, etPhone, etAddress;
    private Button btnSaveChanges, btnNotificationSettings, btnChangePassword, btnLogout;

    private final ActivityResultLauncher<Intent> photoPickerLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Uri imageUri = result.getData().getData();
                    ivProfilePhoto.setImageURI(imageUri);
                    Toast.makeText(this, "Photo updated", Toast.LENGTH_SHORT).show();
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_profile);

        btnBack = findViewById(R.id.btnBack);
        ivProfilePhoto = findViewById(R.id.ivProfilePhoto);
        tvChangePhoto = findViewById(R.id.tvChangePhoto);
        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etAddress = findViewById(R.id.etAddress);
        btnSaveChanges = findViewById(R.id.btnSaveChanges);
        btnNotificationSettings = findViewById(R.id.btnNotificationSettings);
        btnChangePassword = findViewById(R.id.btnChangePassword);
        btnLogout = findViewById(R.id.btnLogout);

        // Pre-fill dummy data for guest
        etFullName.setText("John Doe");
        etEmail.setText("guest@example.com");
        etPhone.setText("+1 234 567 8900");
        etAddress.setText("123 Main St, New York, NY 10001");

        // Change photo tap
        tvChangePhoto.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            photoPickerLauncher.launch(intent);
        });

        // Back button
        btnBack.setOnClickListener(v -> finish());

        // Save Changes (demo)
        btnSaveChanges.setOnClickListener(v -> {
            Toast.makeText(this, "Changes saved", Toast.LENGTH_SHORT).show();
        });

        // Notification Settings
        btnNotificationSettings.setOnClickListener(v -> {
            startActivity(new Intent(this, NotificationSettingsActivity.class));
        });

        // Change Password (demo)
        btnChangePassword.setOnClickListener(v -> {
            Toast.makeText(this, "Change Password clicked", Toast.LENGTH_SHORT).show();
        });

        // Logout
        btnLogout.setOnClickListener(v -> {
            Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }
}