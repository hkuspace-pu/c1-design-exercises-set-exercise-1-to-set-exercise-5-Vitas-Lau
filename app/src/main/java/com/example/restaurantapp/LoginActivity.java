// File: app/src/main/java/com/example/restaurantapp/LoginActivity.java

package com.example.restaurantapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.restaurantapp.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // UI-only for now – no logic yet
        // We will add click listeners when we implement navigation
    }
}