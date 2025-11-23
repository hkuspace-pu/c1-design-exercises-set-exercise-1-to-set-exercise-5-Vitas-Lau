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

        // Dummy samples with category, description, and vegetarian flag
        menuItems.add(new MenuItem("Garlic Bread", 6.99, R.drawable.placeholder_food, true, "Starters",
                "Freshly baked artisan bread brushed with garlic-infused olive oil and topped with Italian herbs. Served warm with a side of marinara sauce for dipping.\n\nPerfect for sharing or as an appetizer before your main course.", true));
        menuItems.add(new MenuItem("Spring Rolls", 8.99, R.drawable.placeholder_food, true, "Starters",
                "Crispy vegetable spring rolls served with sweet chili dipping sauce.", true));
        menuItems.add(new MenuItem("Bruschetta", 7.99, R.drawable.placeholder_food, true, "Starters",
                "Toasted bread topped with fresh tomatoes, basil, garlic, and olive oil.", true));
        menuItems.add(new MenuItem("Caesar Salad", 9.49, R.drawable.placeholder_food, true, "Starters",
                "Romaine lettuce with Caesar dressing, croutons, and parmesan cheese.", true));
        menuItems.add(new MenuItem("Cheese Nachos", 10.99, R.drawable.placeholder_food, false, "Starters",
                "Tortilla chips topped with melted cheese, jalapeños, and sour cream.", true));

        menuItems.add(new MenuItem("Spaghetti Carbonara", 18.99, R.drawable.placeholder_food, true, "Mains",
                "Classic Italian pasta with eggs, cheese, pancetta, and black pepper.", false));
        menuItems.add(new MenuItem("Grilled Ribeye Steak", 32.99, R.drawable.placeholder_food, false, "Mains",
                "Premium ribeye steak grilled to perfection, served with vegetables and potatoes.", false));
        menuItems.add(new MenuItem("Grilled Salmon", 28.99, R.drawable.placeholder_food, false, "Mains",
                "Fresh Atlantic salmon grilled with lemon butter sauce and seasonal vegetables.", false));
        menuItems.add(new MenuItem("Vegetable Stir Fry", 15.49, R.drawable.placeholder_food, true, "Mains",
                "Mixed vegetables stir-fried in garlic soy sauce, served with rice.", true));
        menuItems.add(new MenuItem("Chicken Curry", 17.99, R.drawable.placeholder_food, true, "Mains",
                "Tender chicken in fragrant curry sauce with rice and naan.", false));
        menuItems.add(new MenuItem("Beef Burger", 14.99, R.drawable.placeholder_food, true, "Mains",
                "Juicy beef patty with lettuce, tomato, onion, and special sauce.", false));

        menuItems.add(new MenuItem("Chocolate Lava Cake", 7.99, R.drawable.placeholder_food, true, "Desserts",
                "Warm chocolate cake with molten center, served with vanilla ice cream.", true));
        menuItems.add(new MenuItem("Tiramisu", 8.49, R.drawable.placeholder_food, true, "Desserts",
                "Classic Italian dessert with coffee-soaked ladyfingers and mascarpone.", true));
        menuItems.add(new MenuItem("Cheesecake", 6.99, R.drawable.placeholder_food, false, "Desserts",
                "Creamy New York style cheesecake with berry compote.", true));
        menuItems.add(new MenuItem("Ice Cream Sundae", 5.99, R.drawable.placeholder_food, true, "Desserts",
                "Vanilla ice cream with chocolate sauce, whipped cream, and cherry.", true));

        menuItems.add(new MenuItem("Coca Cola", 2.99, R.drawable.placeholder_food, true, "Drinks",
                "Chilled Coca Cola.", true));
        menuItems.add(new MenuItem("Lemonade", 3.49, R.drawable.placeholder_food, true, "Drinks",
                "Fresh homemade lemonade.", true));
        menuItems.add(new MenuItem("Red Wine", 25.99, R.drawable.placeholder_food, false, "Drinks",
                "House red wine.", true));
        menuItems.add(new MenuItem("Espresso", 4.99, R.drawable.placeholder_food, true, "Drinks",
                "Strong Italian espresso.", true));
        menuItems.add(new MenuItem("Green Tea", 3.99, R.drawable.placeholder_food, true, "Drinks",
                "Premium green tea.", true));

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

    // MenuItem model with category, description, and vegetarian flag
    public static class MenuItem {
        public String name;
        public double price;
        public int imageRes;
        public boolean available;
        public String category;
        public String description;
        public boolean isVegetarian;

        public MenuItem(String name, double price, int imageRes, boolean available, String category, String description, boolean isVegetarian) {
            this.name = name;
            this.price = price;
            this.imageRes = imageRes;
            this.available = available;
            this.category = category;
            this.description = description;
            this.isVegetarian = isVegetarian;
        }
    }
}