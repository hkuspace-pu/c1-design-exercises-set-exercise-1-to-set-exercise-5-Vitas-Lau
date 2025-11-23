package com.example.restauranthub.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.restauranthub.R;
import com.example.restauranthub.adapter.MenuItemAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.List;

public class MenuManageActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private FloatingActionButton fabAddItem;
    private TabLayout tabCategories;
    private RecyclerView rvMenuItems;
    private MenuItemAdapter adapter;
    private List<MenuItem> allMenuItems = new ArrayList<>();
    private List<MenuItem> filteredItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_manage);

        btnBack = findViewById(R.id.btnBack);
        fabAddItem = findViewById(R.id.fabAddItem);
        tabCategories = findViewById(R.id.tabCategories);
        rvMenuItems = findViewById(R.id.rvMenuItems);

        rvMenuItems.setLayoutManager(new GridLayoutManager(this, 2));

        // Dummy data with category
        allMenuItems.add(new MenuItem("Garlic Bread", 6.99, R.drawable.placeholder_food, true, "Starters"));
        allMenuItems.add(new MenuItem("Spring Rolls", 8.99, R.drawable.placeholder_food, true, "Starters"));
        allMenuItems.add(new MenuItem("Bruschetta", 7.99, R.drawable.placeholder_food, true, "Starters"));
        allMenuItems.add(new MenuItem("Caesar Salad", 9.49, R.drawable.placeholder_food, true, "Starters"));
        allMenuItems.add(new MenuItem("Cheese Nachos", 10.99, R.drawable.placeholder_food, false, "Starters"));

        allMenuItems.add(new MenuItem("Spaghetti Carbonara", 18.99, R.drawable.placeholder_food, true, "Mains"));
        allMenuItems.add(new MenuItem("Grilled Ribeye Steak", 32.99, R.drawable.placeholder_food, false, "Mains"));
        allMenuItems.add(new MenuItem("Grilled Salmon", 28.99, R.drawable.placeholder_food, false, "Mains"));
        allMenuItems.add(new MenuItem("Vegetable Stir Fry", 15.49, R.drawable.placeholder_food, true, "Mains"));
        allMenuItems.add(new MenuItem("Chicken Curry", 17.99, R.drawable.placeholder_food, true, "Mains"));
        allMenuItems.add(new MenuItem("Beef Burger", 14.99, R.drawable.placeholder_food, true, "Mains"));

        allMenuItems.add(new MenuItem("Chocolate Lava Cake", 7.99, R.drawable.placeholder_food, true, "Desserts"));
        allMenuItems.add(new MenuItem("Tiramisu", 8.49, R.drawable.placeholder_food, true, "Desserts"));
        allMenuItems.add(new MenuItem("Cheesecake", 6.99, R.drawable.placeholder_food, false, "Desserts"));
        allMenuItems.add(new MenuItem("Ice Cream Sundae", 5.99, R.drawable.placeholder_food, true, "Desserts"));

        allMenuItems.add(new MenuItem("Coca Cola", 2.99, R.drawable.placeholder_food, true, "Drinks"));
        allMenuItems.add(new MenuItem("Lemonade", 3.49, R.drawable.placeholder_food, true, "Drinks"));
        allMenuItems.add(new MenuItem("Red Wine", 25.99, R.drawable.placeholder_food, false, "Drinks"));
        allMenuItems.add(new MenuItem("Espresso", 4.99, R.drawable.placeholder_food, true, "Drinks"));
        allMenuItems.add(new MenuItem("Green Tea", 3.99, R.drawable.placeholder_food, true, "Drinks"));

        adapter = new MenuItemAdapter(allMenuItems, true, new MenuItemAdapter.OnItemClickListener() {
            @Override
            public void onEditClick(MenuItem item) {
                Intent intent = new Intent(MenuManageActivity.this, EditMenuItemActivity.class);
                intent.putExtra("dishName", item.name);
                intent.putExtra("price", item.price);
                intent.putExtra("imageRes", item.imageRes);
                intent.putExtra("available", item.available);
                intent.putExtra("category", item.category); // Pass category if needed
                startActivity(intent);
            }

            @Override
            public void onDeleteClick(MenuItem item) {
                allMenuItems.remove(item);
                filterMenuItems(tabCategories.getSelectedTabPosition() == 0 ? "All" : tabCategories.getTabAt(tabCategories.getSelectedTabPosition()).getText().toString());
            }
        });
        rvMenuItems.setAdapter(adapter);

        // Setup Tabs
        tabCategories.addTab(tabCategories.newTab().setText("All"));
        tabCategories.addTab(tabCategories.newTab().setText("Starters"));
        tabCategories.addTab(tabCategories.newTab().setText("Mains"));
        tabCategories.addTab(tabCategories.newTab().setText("Desserts"));
        tabCategories.addTab(tabCategories.newTab().setText("Drinks"));

        // Initial filter - All
        filterMenuItems("All");

        tabCategories.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String category = tab.getText().toString();
                filterMenuItems(category);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                String category = tab.getText().toString();
                filterMenuItems(category);
            }
        });

        // FAB - Add Item
        fabAddItem.setOnClickListener(v -> {
            Intent intent = new Intent(MenuManageActivity.this, AddMenuItemActivity.class);
            startActivity(intent);
        });

        // Back button
        btnBack.setOnClickListener(v -> finish());
    }

    private void filterMenuItems(String category) {
        filteredItems.clear();
        for (MenuItem item : allMenuItems) {
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