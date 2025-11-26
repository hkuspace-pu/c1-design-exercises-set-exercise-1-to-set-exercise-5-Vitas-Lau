package com.example.restauranthub.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.restauranthub.R;
import com.example.restauranthub.adapter.GuestReservationAdapter;
import com.example.restauranthub.viewmodel.MyReservationsViewModel;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;

public class MyReservationsActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private TabLayout tabReservations;
    private RecyclerView rvReservations;
    private View cardEmptyState;
    private Button btnBookTableEmpty;
    private TextView tvEmptyTitle;
    private GuestReservationAdapter adapter;
    private MyReservationsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reservations);

        viewModel = new ViewModelProvider(this).get(MyReservationsViewModel.class);

        btnBack = findViewById(R.id.btnBack);
        tabReservations = findViewById(R.id.tabReservations);
        rvReservations = findViewById(R.id.rvReservations);
        cardEmptyState = findViewById(R.id.cardEmptyState);
        tvEmptyTitle = findViewById(R.id.tvEmptyTitle);
        btnBookTableEmpty = findViewById(R.id.btnBookTableEmpty);

        rvReservations.setLayoutManager(new LinearLayoutManager(this));
        adapter = new GuestReservationAdapter(this, new ArrayList<>());
        rvReservations.setAdapter(adapter);

        // Observe upcoming reservations
        viewModel.getUpcomingReservations().observe(this, reservations -> {
            if (tabReservations.getSelectedTabPosition() == 0) {
                adapter.updateReservations(reservations);
                updateEmptyState(reservations.isEmpty());
            }
        });

        // Observe past reservations
        viewModel.getPastReservations().observe(this, reservations -> {
            if (tabReservations.getSelectedTabPosition() == 1) {
                adapter.updateReservations(reservations);
                updateEmptyState(reservations.isEmpty());
            }
        });


        viewModel.loadReservations();


        tabReservations.addTab(tabReservations.newTab().setText("Upcoming"));
        tabReservations.addTab(tabReservations.newTab().setText("Past"));

        tabReservations.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    adapter.updateReservations(viewModel.getUpcomingReservations().getValue());
                    updateEmptyState(viewModel.getUpcomingReservations().getValue().isEmpty());
                } else {
                    adapter.updateReservations(viewModel.getPastReservations().getValue());
                    updateEmptyState(viewModel.getPastReservations().getValue().isEmpty());
                }
            }

            @Override public void onTabUnselected(TabLayout.Tab tab) {}
            @Override public void onTabReselected(TabLayout.Tab tab) {}
        });

        btnBookTableEmpty.setOnClickListener(v -> {
            startActivity(new Intent(this, BookTableActivity.class));
        });

        btnBack.setOnClickListener(v -> finish());
    }

    private void updateEmptyState(boolean isEmpty) {
        if (isEmpty) {
            rvReservations.setVisibility(View.GONE);
            cardEmptyState.setVisibility(View.VISIBLE);
            tvEmptyTitle.setText(tabReservations.getSelectedTabPosition() == 0 ?
                    "No Upcoming Reservations" : "No Past Reservations");
        } else {
            rvReservations.setVisibility(View.VISIBLE);
            cardEmptyState.setVisibility(View.GONE);
        }
    }
}