package com.example.restauranthub.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.restauranthub.R;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class EditReservationActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private TextView tvGuestName, tvPhone;
    private Spinner spinnerDate, spinnerTime, spinnerPartySize, spinnerTableAssignment, spinnerStatus;
    private EditText etSpecialRequests;
    private Button btnSaveChanges, btnCancelReservation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_reservation);

        btnBack = findViewById(R.id.btnBack);
        tvGuestName = findViewById(R.id.tvGuestName);
        tvPhone = findViewById(R.id.tvPhone);
        spinnerDate = findViewById(R.id.spinnerDate);
        spinnerTime = findViewById(R.id.spinnerTime);
        spinnerPartySize = findViewById(R.id.spinnerPartySize);
        spinnerTableAssignment = findViewById(R.id.spinnerTableAssignment);
        etSpecialRequests = findViewById(R.id.etSpecialRequests);
        spinnerStatus = findViewById(R.id.spinnerStatus);
        btnSaveChanges = findViewById(R.id.btnSaveChanges);
        btnCancelReservation = findViewById(R.id.btnCancelReservation);

        // Pre-fill from intent
        tvGuestName.setText(getIntent().getStringExtra("guestName"));
        tvPhone.setText(getIntent().getStringExtra("phone"));

        // Setup Spinners
        setupDateSpinner();
        setupTimeSpinner();

        ArrayAdapter<String> partyAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[]{"Select Party Size", "2 guests", "4 guests", "6 guests"});
        spinnerPartySize.setAdapter(partyAdapter);

        ArrayAdapter<String> tableAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[]{"No Assignment", "Table 1", "Table 2", "Table 3"});
        spinnerTableAssignment.setAdapter(tableAdapter);

        ArrayAdapter<String> statusAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[]{"Confirmed", "Pending", "Cancelled"});
        spinnerStatus.setAdapter(statusAdapter);

        // Back button navigation
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Save Changes (basic toast for demo)
        btnSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EditReservationActivity.this, "Changes saved", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        // Cancel Reservation (basic toast for demo)
        btnCancelReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EditReservationActivity.this, "Reservation cancelled", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void setupDateSpinner() {
        List<String> dates = new ArrayList<>();
        dates.add("Select a date");

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM dd, yyyy", Locale.getDefault());

        for (int i = 0; i < 30; i++) {
            dates.add(sdf.format(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }

        ArrayAdapter<String> dateAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dates);
        spinnerDate.setAdapter(dateAdapter);
    }

    private void setupTimeSpinner() {
        List<String> times = new ArrayList<>();
        times.add("Select Time");
        // Add times from 11:00 to 22:00 in 30-minute intervals
        for (int h = 11; h < 22; h++) {
            times.add(String.format(Locale.getDefault(), "%02d:00", h));
            times.add(String.format(Locale.getDefault(), "%02d:30", h));
        }
        ArrayAdapter<String> timeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, times);
        spinnerTime.setAdapter(timeAdapter);
    }
}