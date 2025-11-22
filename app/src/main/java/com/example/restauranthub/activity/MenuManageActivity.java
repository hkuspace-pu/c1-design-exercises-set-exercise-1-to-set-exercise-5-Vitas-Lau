package com.example.restauranthub.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.restauranthub.R;
import com.example.restauranthub.adapter.MenuItemAdapter;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.List;

public class MenuManageActivity extends AppCompatActivity {

    private RecyclerView rvMenuItems;
    private MenuItemAdapter adapter;
    private List<MenuItem> menuItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_manage);

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

        // Dummy samples for testing (expanded with more items)
        menuItems = new ArrayList<>();
        // Starters
        menuItems.add(new MenuItem("Garlic Bread", 6.99, R.drawable.garlic_bread, true));
        menuItems.add(new MenuItem("Spring Rolls", 8.99, R.drawable.spring_rolls, true));
        menuItems.add(new MenuItem("Bruschetta", 7.99, R.drawable.bruschetta, true));
        menuItems.add(new MenuItem("Caesar Salad", 9.49, R.drawable.caesar_salad, true));
        menuItems.add(new MenuItem("Cheese Nachos", 10.99, R.drawable.cheese_nachos, false));

        // Mains
        menuItems.add(new MenuItem("Spaghetti Carbonara", 18.99, R.drawable.spaghetti_carbonara, true));
        menuItems.add(new MenuItem("Grilled Ribeye Steak", 32.99, R.drawable.grilled_ribeye, false));
        menuItems.add(new MenuItem("Grilled Salmon", 28.99, R.drawable.grilled_salmon, false));
        menuItems.add(new MenuItem("Vegetable Stir Fry", 15.49, R.drawable.vegetable_stir_fry, true));
        menuItems.add(new MenuItem("Chicken Curry", 17.99, R.drawable.chicken_curry, true));
        menuItems.add(new MenuItem("Beef Burger", 14.99, R.drawable.beef_burger, true));

        // Desserts
        menuItems.add(new MenuItem("Chocolate Lava Cake", 7.99, R.drawable.chocolate_lava_cake, true));
        menuItems.add(new MenuItem("Tiramisu", 8.49, R.drawable.tiramisu, true));
        menuItems.add(new MenuItem("Cheesecake", 6.99, R.drawable.cheesecake, false));
        menuItems.add(new MenuItem("Ice Cream Sundae", 5.99, R.drawable.ice_cream_sundae, true));

        // Drinks
        menuItems.add(new MenuItem("Coca Cola", 2.99, R.drawable.coca_cola, true));
        menuItems.add(new MenuItem("Lemonade", 3.49, R.drawable.lemonade, true));
        menuItems.add(new MenuItem("Red Wine", 25.99, R.drawable.red_wine, false));
        menuItems.add(new MenuItem("Espresso", 4.99, R.drawable.espresso, true));
        menuItems.add(new MenuItem("Green Tea", 3.99, R.drawable.green_tea, true));

        adapter = new MenuItemAdapter(menuItems, true); // true for staff/manage mode
        rvMenuItems.setAdapter(adapter);
    }

    // Simple POJO for menu item (for dummy data)
    public static class MenuItem {
        String name;
        double price;
        int imageRes;
        boolean available;

        public MenuItem(String name, double price, int imageRes, boolean available) {
            this.name = name;
            this.price = price;
            this.imageRes = imageRes;
            this.available = available;
        }
    }
}