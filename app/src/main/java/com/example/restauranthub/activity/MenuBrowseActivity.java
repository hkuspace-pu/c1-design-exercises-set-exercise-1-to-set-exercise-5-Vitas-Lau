package com.example.restauranthub.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import com.example.restauranthub.R;

public class MenuBrowseActivity extends AppCompatActivity {

    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_browse);

        // Find back button (if added to layout)
        btnBack = findViewById(R.id.btnBack);

        // Set click listener for back navigation
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close current activity and go back
            }
        });

        // Add your other setup code here (e.g., tabs, RecyclerView)
    }
}