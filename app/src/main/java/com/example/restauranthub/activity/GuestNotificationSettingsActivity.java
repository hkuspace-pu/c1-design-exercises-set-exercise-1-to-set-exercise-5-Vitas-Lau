package com.example.restauranthub.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.restauranthub.R;

public class GuestNotificationSettingsActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private Switch switchEnableAll;
    private CheckBox cbReservationConfirmation, cbReservationReminders, cbMenuUpdates, cbSpecialOffers;
    private Switch switchPushNotifications, switchEmail, switchSMS;
    private Button btnSavePreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_notification_settings);

        btnBack = findViewById(R.id.btnBack);
        switchEnableAll = findViewById(R.id.switchEnableAll);
        cbReservationConfirmation = findViewById(R.id.cbReservationConfirmation);
        cbReservationReminders = findViewById(R.id.cbReservationReminders);
        cbMenuUpdates = findViewById(R.id.cbMenuUpdates);
        cbSpecialOffers = findViewById(R.id.cbSpecialOffers);
        switchPushNotifications = findViewById(R.id.switchPushNotifications);
        switchEmail = findViewById(R.id.switchEmail);
        switchSMS = findViewById(R.id.switchSMS);
        btnSavePreferences = findViewById(R.id.btnSavePreferences);

        // Master toggle - enable/disable all checkboxes
        switchEnableAll.setOnCheckedChangeListener((buttonView, isChecked) -> {
            cbReservationConfirmation.setChecked(isChecked);
            cbReservationReminders.setChecked(isChecked);
            cbMenuUpdates.setChecked(isChecked);
            cbSpecialOffers.setChecked(isChecked);
        });

        // Individual checkboxes - update master toggle
        cbReservationConfirmation.setOnCheckedChangeListener((buttonView, isChecked) -> updateMasterToggle());
        cbReservationReminders.setOnCheckedChangeListener((buttonView, isChecked) -> updateMasterToggle());
        cbMenuUpdates.setOnCheckedChangeListener((buttonView, isChecked) -> updateMasterToggle());
        cbSpecialOffers.setOnCheckedChangeListener((buttonView, isChecked) -> updateMasterToggle());

        // Back button
        btnBack.setOnClickListener(v -> finish());

        // Save preferences (demo toast)
        btnSavePreferences.setOnClickListener(v -> {
            Toast.makeText(this, "Preferences saved", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    private void updateMasterToggle() {
        boolean allChecked = cbReservationConfirmation.isChecked() && cbReservationReminders.isChecked() &&
                cbMenuUpdates.isChecked() && cbSpecialOffers.isChecked();
        switchEnableAll.setChecked(allChecked);
    }
}