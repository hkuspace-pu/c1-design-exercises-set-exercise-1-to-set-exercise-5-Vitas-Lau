package com.example.restauranthub.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.restauranthub.R;
import com.example.restauranthub.adapter.SettingsAdapter;
import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private RecyclerView rvSettings;
    private SettingsAdapter adapter;
    private List<SettingsItem> settingsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        btnBack = findViewById(R.id.btnBack);
        rvSettings = findViewById(R.id.rvSettings);

        rvSettings.setLayoutManager(new LinearLayoutManager(this));

        // Dummy settings items (removed non-existent activities; add when created)
        settingsList.add(new SettingsItem("My Profile", "View and edit your profile information", R.drawable.ic_person_purple, ProfileActivity.class));
        settingsList.add(new SettingsItem("Notification Settings", "Manage your notification preferences", R.drawable.ic_notification, NotificationSettingsActivity.class));
        // Add Security and Help when activities are created
        // settingsList.add(new SettingsItem("Security", "Change password and security options", R.drawable.ic_security, SecurityActivity.class));
        // settingsList.add(new SettingsItem("Help & Support", "Get help and contact support", R.drawable.ic_help, HelpSupportActivity.class));

        adapter = new SettingsAdapter(settingsList);
        rvSettings.setAdapter(adapter);

        // Back button
        btnBack.setOnClickListener(v -> finish());
    }

    // Simple model for settings item - make fields public
    public static class SettingsItem {
        public String title;
        public String subtitle;
        public int iconRes;
        public Class<?> destinationActivity;

        public SettingsItem(String title, String subtitle, int iconRes, Class<?> destinationActivity) {
            this.title = title;
            this.subtitle = subtitle;
            this.iconRes = iconRes;
            this.destinationActivity = destinationActivity;
        }
    }
}