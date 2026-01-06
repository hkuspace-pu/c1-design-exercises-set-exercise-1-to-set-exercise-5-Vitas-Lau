package com.example.restauranthub.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.restauranthub.R;
import com.example.restauranthub.adapter.GuestMenuAdapter;
import com.example.restauranthub.model.MenuItem;
import com.example.restauranthub.repository.MenuRepository;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.List;

public class MenuBrowseActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private TabLayout tabCategories;
    private RecyclerView rvMenu;
    private GuestMenuAdapter adapter;
    private List<MenuItem> menuItems = new ArrayList<>();
    private List<MenuItem> filteredItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_browse);

        btnBack = findViewById(R.id.btnBack);
        tabCategories = findViewById(R.id.tabCategories);
        rvMenu = findViewById(R.id.rvMenu);

        rvMenu.setLayoutManager(new GridLayoutManager(this, 2));

        // Get data from repository
        menuItems = MenuRepository.getInstance().getMenuItems();

        adapter = new GuestMenuAdapter(menuItems);
        rvMenu.setAdapter(adapter);

        // Setup Tabs
        tabCategories.addTab(tabCategories.newTab().setText("All"));
        tabCategories.addTab(tabCategories.newTab().setText("Starters"));
        tabCategories.addTab(tabCategories.newTab().setText("Mains"));
        tabCategories.addTab(tabCategories.newTab().setText("Desserts"));
        tabCategories.addTab(tabCategories.newTab().setText("Drinks"));

        tabCategories.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                filterMenuItems(tab.getText().toString());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                filterMenuItems(tab.getText().toString());
            }
        });

        // Initial filter - show All
        filterMenuItems("All");

        btnBack.setOnClickListener(v -> finish());
    }

    private void filterMenuItems(String category) {
        filteredItems.clear();
        for (MenuItem item : menuItems) {
            if ("All".equals(category) || category.equals(item.category)) {
                filteredItems.add(item);
            }
        }
        adapter.updateMenuItems(filteredItems);
    }
}