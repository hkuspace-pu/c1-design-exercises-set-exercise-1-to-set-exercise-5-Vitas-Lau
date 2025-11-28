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
import com.example.restauranthub.model.MenuItem;
import com.example.restauranthub.repository.MenuRepository;
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
    private MenuRepository menuRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_manage);

        btnBack = findViewById(R.id.btnBack);
        fabAddItem = findViewById(R.id.fabAddItem);
        tabCategories = findViewById(R.id.tabCategories);
        rvMenuItems = findViewById(R.id.rvMenuItems);

        rvMenuItems.setLayoutManager(new GridLayoutManager(this, 2));

        menuRepository = MenuRepository.getInstance();
        allMenuItems = menuRepository.getMenuItems();

        adapter = new MenuItemAdapter(allMenuItems, true, new MenuItemAdapter.OnItemClickListener() {
            @Override
            public void onEditClick(MenuItem item) {
                Intent intent = new Intent(MenuManageActivity.this, EditMenuItemActivity.class);
                intent.putExtra("dishName", item.name);
                intent.putExtra("price", item.price);
                intent.putExtra("imageRes", item.imageRes);
                intent.putExtra("available", item.available);
                intent.putExtra("category", item.category);
                startActivity(intent);
            }

            @Override
            public void onDeleteClick(MenuItem item) {
                menuRepository.removeMenuItem(item);
                filterMenuItems(tabCategories.getTabAt(tabCategories.getSelectedTabPosition()).getText().toString());
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

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh the list in case items were added or edited
        filterMenuItems(tabCategories.getTabAt(tabCategories.getSelectedTabPosition()).getText().toString());
    }

    private void filterMenuItems(String category) {
        allMenuItems = menuRepository.getMenuItems(); // Get the latest data
        filteredItems.clear();
        for (MenuItem item : allMenuItems) {
            if ("All".equals(category) || category.equals(item.category)) {
                filteredItems.add(item);
            }
        }
        adapter.updateMenuItems(filteredItems);
    }
}