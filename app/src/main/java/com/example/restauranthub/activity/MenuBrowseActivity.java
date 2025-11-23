package com.example.restauranthub.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.restauranthub.R;
import com.example.restauranthub.adapter.GuestMenuAdapter; // New adapter for guest
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

        // Dummy samples with category
        menuItems.add(new MenuItem("Garlic Bread", 6.99, R.drawable.placeholder_food, true, "Starters"));
        menuItems.add(new MenuItem("Spring Rolls", 8.99, R.drawable.placeholder_food, true, "Starters"));
        menuItems.add(new MenuItem("Bruschetta", 7.99, R.drawable.placeholder_food, true, "Starters"));
        menuItems.add(new MenuItem("Caesar Salad", 9.49, R.drawable.placeholder_food, true, "Starters"));
        menuItems.add(new MenuItem("Cheese Nachos", 10.99, R.drawable.placeholder_food, false, "Starters"));

        menuItems.add(new MenuItem("Spaghetti Carbonara", 18.99, R.drawable.placeholder_food, true, "Mains"));
        menuItems.add(new MenuItem("Grilled Ribeye Steak", 32.99, R.drawable.placeholder_food, false, "Mains"));
        menuItems.add(new MenuItem("Grilled Salmon", 28.99, R.drawable.placeholder_food, false, "Mains"));
        menuItems.add(new MenuItem("Vegetable Stir Fry", 15.49, R.drawable.placeholder_food, true, "Mains"));
        menuItems.add(new MenuItem("Chicken Curry", 17.99, R.drawable.placeholder_food, true, "Mains"));
        menuItems.add(new MenuItem("Beef Burger", 14.99, R.drawable.placeholder_food, true, "Mains"));

        menuItems.add(new MenuItem("Chocolate Lava Cake", 7.99, R.drawable.placeholder_food, true, "Desserts"));
        menuItems.add(new MenuItem("Tiramisu", 8.49, R.drawable.placeholder_food, true, "Desserts"));
        menuItems.add(new MenuItem("Cheesecake", 6.99, R.drawable.placeholder_food, false, "Desserts"));
        menuItems.add(new MenuItem("Ice Cream Sundae", 5.99, R.drawable.placeholder_food, true, "Desserts"));

        menuItems.add(new MenuItem("Coca Cola", 2.99, R.drawable.placeholder_food, true, "Drinks"));
        menuItems.add(new MenuItem("Lemonade", 3.49, R.drawable.placeholder_food, true, "Drinks"));
        menuItems.add(new MenuItem("Red Wine", 25.99, R.drawable.placeholder_food, false, "Drinks"));
        menuItems.add(new MenuItem("Espresso", 4.99, R.drawable.placeholder_food, true, "Drinks"));
        menuItems.add(new MenuItem("Green Tea", 3.99, R.drawable.placeholder_food, true, "Drinks"));

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

    // MenuItem model with category
    public static class MenuItem {
        public String name;
        public double price;
        public int imageRes;
        public boolean available;
        public String category;

        public MenuItem(String name, double price, int imageRes, boolean available, String category) {
            this.name = name;
            this.price = price;
            this.imageRes = imageRes;
            this.available = available;
            this.category = category;
        }
    }
}