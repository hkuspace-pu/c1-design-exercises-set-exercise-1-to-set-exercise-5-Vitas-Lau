package com.example.restauranthub.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.restauranthub.R;
import com.example.restauranthub.adapter.TimeSlotAdapter;
import java.util.ArrayList;
import java.util.List;

public class BookTableActivity extends AppCompatActivity implements TimeSlotAdapter.OnTimeSlotClickListener {

    private ImageButton btnBack;
    private Spinner spinnerDate, spinnerPartySize;
    private RecyclerView rvTimeSlots;
    private TimeSlotAdapter timeSlotAdapter;
    private Button btnConfirmBooking;
    private String selectedTime = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_table);

        btnBack = findViewById(R.id.btnBack);
        spinnerDate = findViewById(R.id.spinnerDate);
        spinnerPartySize = findViewById(R.id.spinnerPartySize);
        rvTimeSlots = findViewById(R.id.rvTimeSlots);
        btnConfirmBooking = findViewById(R.id.btnConfirmBooking);

        rvTimeSlots.setLayoutManager(new GridLayoutManager(this, 2));

        // Dummy time slots
        List<TimeSlotAdapter.TimeSlot> timeSlots = new ArrayList<>();
        timeSlots.add(new TimeSlotAdapter.TimeSlot("11:00", true));
        timeSlots.add(new TimeSlotAdapter.TimeSlot("11:30", true));
        timeSlots.add(new TimeSlotAdapter.TimeSlot("12:00", false));
        timeSlots.add(new TimeSlotAdapter.TimeSlot("12:30", true));
        timeSlots.add(new TimeSlotAdapter.TimeSlot("13:00", true));
        timeSlots.add(new TimeSlotAdapter.TimeSlot("13:30", true));
        timeSlots.add(new TimeSlotAdapter.TimeSlot("14:00", false));
        timeSlots.add(new TimeSlotAdapter.TimeSlot("14:30", true));
        timeSlots.add(new TimeSlotAdapter.TimeSlot("15:00", true));
        timeSlots.add(new TimeSlotAdapter.TimeSlot("15:30", true));

        timeSlotAdapter = new TimeSlotAdapter(timeSlots, this); // Pass listener
        rvTimeSlots.setAdapter(timeSlotAdapter);

        // Date spinner
        ArrayAdapter<String> dateAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,
                new String[]{"Select a date", "Sun, Nov 23, 2025", "Mon, Nov 24, 2025", "Tue, Nov 25, 2025"});
        spinnerDate.setAdapter(dateAdapter);

        // Party size spinner
        ArrayAdapter<String> partyAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,
                new String[]{"2 Guests", "3 Guests", "4 Guests", "5 Guests", "6 Guests", "7 Guests", "8 Guests"});
        spinnerPartySize.setAdapter(partyAdapter);

        // Back button
        btnBack.setOnClickListener(v -> finish());

        // Confirm booking - check if time selected
        btnConfirmBooking.setOnClickListener(v -> {
            if (selectedTime == null) {
                Toast.makeText(this, "Please select a time slot", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Booking confirmed for " + selectedTime, Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @Override
    public void onTimeSlotSelected(String time) {
        selectedTime = time;
    }
}