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
        // TODO: Pre-select spinners based on extras (e.g., spinnerDate.setSelection(index))

        // Setup Spinners with dummy options
        ArrayAdapter<String> dateAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[]{"Select Date", "Wed, Nov 19, 2025", "Thu, Nov 20, 2025", "Fri, Nov 21, 2025"});
        spinnerDate.setAdapter(dateAdapter);

        ArrayAdapter<String> timeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[]{"Select Time", "18:30", "19:00", "20:00"});
        spinnerTime.setAdapter(timeAdapter);

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
}