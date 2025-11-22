package com.example.restauranthub.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

    private FloatingActionButton fabAddItem;
    private RecyclerView rvMenuItems;
    private MenuItemAdapter adapter;
    private List<MenuItem> menuItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_manage);

        // Setup FAB for Add
        fabAddItem = findViewById(R.id.fabAddItem);
        fabAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuManageActivity.this, AddMenuItemActivity.class);
                startActivity(intent);
            }
        });

        // Setup Tabs
        TabLayout tabCategories = findViewById(R.id.tabCategories);
        tabCategories.addTab(tabCategories.newTab().setText("All"));
        tabCategories.addTab(tabCategories.newTab().setText("Starters"));
        tabCategories.addTab(tabCategories.newTab().setText("Mains"));
        tabCategories.addTab(tabCategories.newTab().setText("Desserts"));
        tabCategories.addTab(tabCategories.newTab().setText("Drinks"));

        // Setup RecyclerView (Grid with 2 columns)
        rvMenuItems = findViewById(R.id.rvMenuItems);
        rvMenuItems.setLayoutManager(new GridLayoutManager(this, 2));

        // Dummy samples for testing (expanded with more items, using placeholder)
        menuItems = new ArrayList<>();
        // Starters
        menuItems.add(new MenuItem("Garlic Bread", 6.99, R.drawable.placeholder_food, true));
        menuItems.add(new MenuItem("Spring Rolls", 8.99, R.drawable.placeholder_food, true));
        menuItems.add(new MenuItem("Bruschetta", 7.99, R.drawable.placeholder_food, true));
        menuItems.add(new MenuItem("Caesar Salad", 9.49, R.drawable.placeholder_food, true));
        menuItems.add(new MenuItem("Cheese Nachos", 10.99, R.drawable.placeholder_food, false));

        // Mains
        menuItems.add(new MenuItem("Spaghetti Carbonara", 18.99, R.drawable.placeholder_food, true));
        menuItems.add(new MenuItem("Grilled Ribeye Steak", 32.99, R.drawable.placeholder_food, false));
        menuItems.add(new MenuItem("Grilled Salmon", 28.99, R.drawable.placeholder_food, false));
        menuItems.add(new MenuItem("Vegetable Stir Fry", 15.49, R.drawable.placeholder_food, true));
        menuItems.add(new MenuItem("Chicken Curry", 17.99, R.drawable.placeholder_food, true));
        menuItems.add(new MenuItem("Beef Burger", 14.99, R.drawable.placeholder_food, true));

        // Desserts
        menuItems.add(new MenuItem("Chocolate Lava Cake", 7.99, R.drawable.placeholder_food, true));
        menuItems.add(new MenuItem("Tiramisu", 8.49, R.drawable.placeholder_food, true));
        menuItems.add(new MenuItem("Cheesecake", 6.99, R.drawable.placeholder_food, false));
        menuItems.add(new MenuItem("Ice Cream Sundae", 5.99, R.drawable.placeholder_food, true));

        // Drinks
        menuItems.add(new MenuItem("Coca Cola", 2.99, R.drawable.placeholder_food, true));
        menuItems.add(new MenuItem("Lemonade", 3.49, R.drawable.placeholder_food, true));
        menuItems.add(new MenuItem("Red Wine", 25.99, R.drawable.placeholder_food, false));
        menuItems.add(new MenuItem("Espresso", 4.99, R.drawable.placeholder_food, true));
        menuItems.add(new MenuItem("Green Tea", 3.99, R.drawable.placeholder_food, true));

        adapter = new MenuItemAdapter(menuItems, true, new MenuItemAdapter.OnItemClickListener() {
            @Override
            public void onEditClick(MenuItem item) {
                Intent intent = new Intent(MenuManageActivity.this, EditMenuItemActivity.class);
                // Pass data to edit activity
                intent.putExtra("dishName", item.name);
                intent.putExtra("price", item.price);
                intent.putExtra("imageRes", item.imageRes);
                intent.putExtra("available", item.available);
                // TODO: Pass other fields like description, dietary if added to MenuItem
                startActivity(intent);
            }

            @Override
            public void onDeleteClick(MenuItem item) {
                // TODO: Handle delete logic if needed (e.g., remove from list and notify adapter)
                menuItems.remove(item);
                adapter.notifyDataSetChanged();
            }
        });
        rvMenuItems.setAdapter(adapter);
    }

    // Simple POJO for menu item (for dummy data) - make fields public
    public static class MenuItem {
        public String name;
        public double price;
        public int imageRes;
        public boolean available;

        public MenuItem(String name, double price, int imageRes, boolean available) {
            this.name = name;
            this.price = price;
            this.imageRes = imageRes;
            this.available = available;
        }
    }
}