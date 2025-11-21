package com.example.restaurantapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.example.restaurantapp.adapter.ReservationAdapter;
import com.example.restaurantapp.model.Reservation;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import java.util.*;

public class ReservationsActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private List<List<Reservation>> reservationPages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        prepareDummyData();

        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new ReservationsPagerAdapter());

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0: tab.setText(getString(R.string.today)); break;
                case 1: tab.setText(getString(R.string.upcoming)); break;
                case 2: tab.setText(getString(R.string.past)); break;
            }
        }).attach();
    }

    private void prepareDummyData() {
        Calendar cal = Calendar.getInstance();
        reservationPages.add(Arrays.asList( // Today
                new Reservation("1", "John Smith", cal.getTime(), 4, "+1 (555) 123-4567", "today"),
                new Reservation("2", "Sarah Johnson", cal.getTime(), 2, "+1 (555) 234-5678", "today"),
                new Reservation("3", "Michael Brown", cal.getTime(), 6, "+1 (555) 345-6789", "today")
        ));
        reservationPages.add(new ArrayList<>()); // Upcoming (empty for demo)
        reservationPages.add(new ArrayList<>()); // Past (empty for demo)
    }

    private class ReservationsPagerAdapter extends androidx.viewpager2.adapter.FragmentStateAdapter {
        public ReservationsPagerAdapter() {
            super(ReservationsActivity.this);
        }

        @Override
        public androidx.fragment.app.Fragment createFragment(int position) {
            return ReservationFragment.newInstance(new ArrayList<>(reservationPages.get(position)));
        }

        @Override public int getItemCount() { return 3; }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}