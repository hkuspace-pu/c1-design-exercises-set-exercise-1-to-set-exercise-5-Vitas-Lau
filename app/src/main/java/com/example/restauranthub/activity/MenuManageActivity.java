package com.example.restauranthub.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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
    private MenuItem currentEditingItem = null;

    private final ActivityResultLauncher<Intent> addMenuItemLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        // Extract data from result intent
                        String name = result.getData().getStringExtra("dishName");
                        String description = result.getData().getStringExtra("description"); // description not stored in model yet
                        double price = result.getData().getDoubleExtra("price", 0.0);
                        boolean available = result.getData().getBooleanExtra("available", true);
                        // Default category for now, or get from intent if added
                        String category = "Mains"; 
                        // Default placeholder image for now
                        int imageRes = R.drawable.placeholder_dish;

                        // Create new item and add to list
                        MenuItem newItem = new MenuItem(name, price, imageRes, available, category);
                        allMenuItems.add(newItem);
                        
                        // Refresh list
                        String currentTab = tabCategories.getTabAt(tabCategories.getSelectedTabPosition()).getText().toString();
                        filterMenuItems(currentTab);
                    }
                }
            }
    );

    private final ActivityResultLauncher<Intent> editMenuItemLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null && currentEditingItem != null) {
                        // Update current item
                        currentEditingItem.name = result.getData().getStringExtra("dishName");
                        currentEditingItem.price = result.getData().getDoubleExtra("price", 0.0);
                        currentEditingItem.available = result.getData().getBooleanExtra("available", true);
                        // Update other fields if model supported (description, dietary info)

                        // Refresh list
                        String currentTab = tabCategories.getTabAt(tabCategories.getSelectedTabPosition()).getText().toString();
                        filterMenuItems(currentTab);
                        currentEditingItem = null;
                    }
                }
            }
    );

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
        allMenuItems.add(new MenuItem("Garlic Bread", 6.99, R.drawable.placeholder_dish, true, "Starters"));
        allMenuItems.add(new MenuItem("Spring Rolls", 8.99, R.drawable.placeholder_dish, true, "Starters"));
        allMenuItems.add(new MenuItem("Bruschetta", 7.99, R.drawable.placeholder_dish, true, "Starters"));
        allMenuItems.add(new MenuItem("Caesar Salad", 9.49, R.drawable.placeholder_dish, true, "Starters"));
        allMenuItems.add(new MenuItem("Cheese Nachos", 10.99, R.drawable.placeholder_dish, false, "Starters"));

        allMenuItems.add(new MenuItem("Spaghetti Carbonara", 18.99, R.drawable.placeholder_dish, true, "Mains"));
        allMenuItems.add(new MenuItem("Grilled Ribeye Steak", 32.99, R.drawable.placeholder_dish, false, "Mains"));
        allMenuItems.add(new MenuItem("Grilled Salmon", 28.99, R.drawable.placeholder_dish, false, "Mains"));
        allMenuItems.add(new MenuItem("Vegetable Stir Fry", 15.49, R.drawable.placeholder_dish, true, "Mains"));
        allMenuItems.add(new MenuItem("Chicken Curry", 17.99, R.drawable.placeholder_dish, true, "Mains"));
        allMenuItems.add(new MenuItem("Beef Burger", 14.99, R.drawable.placeholder_dish, true, "Mains"));

        allMenuItems.add(new MenuItem("Chocolate Lava Cake", 7.99, R.drawable.placeholder_dish, true, "Desserts"));
        allMenuItems.add(new MenuItem("Tiramisu", 8.49, R.drawable.placeholder_dish, true, "Desserts"));
        allMenuItems.add(new MenuItem("Cheesecake", 6.99, R.drawable.placeholder_dish, false, "Desserts"));
        allMenuItems.add(new MenuItem("Ice Cream Sundae", 5.99, R.drawable.placeholder_dish, true, "Desserts"));

        allMenuItems.add(new MenuItem("Coca Cola", 2.99, R.drawable.placeholder_dish, true, "Drinks"));
        allMenuItems.add(new MenuItem("Lemonade", 3.49, R.drawable.placeholder_dish, true, "Drinks"));
        allMenuItems.add(new MenuItem("Red Wine", 25.99, R.drawable.placeholder_dish, false, "Drinks"));
        allMenuItems.add(new MenuItem("Espresso", 4.99, R.drawable.placeholder_dish, true, "Drinks"));
        allMenuItems.add(new MenuItem("Green Tea", 3.99, R.drawable.placeholder_dish, true, "Drinks"));

        adapter = new MenuItemAdapter(allMenuItems, true, new MenuItemAdapter.OnItemClickListener() {
            @Override
            public void onEditClick(MenuItem item) {
                currentEditingItem = item;
                Intent intent = new Intent(MenuManageActivity.this, EditMenuItemActivity.class);
                intent.putExtra("dishName", item.name);
                intent.putExtra("price", item.price);
                intent.putExtra("imageRes", item.imageRes);
                intent.putExtra("available", item.available);
                intent.putExtra("category", item.category); // Pass category if needed
                editMenuItemLauncher.launch(intent);
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
            addMenuItemLauncher.launch(intent);
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