package com.example.restaurantapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.restaurantapp.adapter.MenuItemAdapter;
import com.example.restaurantapp.model.MenuItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class ManageMenuActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MenuItemAdapter adapter;
    private List<MenuItem> menuList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_menu);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        recyclerView = findViewById(R.id.recycler_menu);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Dummy data
        menuList = new ArrayList<>();
        menuList.add(new MenuItem("1", "Garlic Bread", 6.99, true, ""));
        menuList.add(new MenuItem("2", "Spring Rolls", 8.99, true, ""));
        menuList.add(new MenuItem("3", "Bruschetta", 7.99, true, ""));
        menuList.add(new MenuItem("4", "Spaghetti Carbonara", 18.99, true, ""));

        adapter = new MenuItemAdapter(menuList);
        recyclerView.setAdapter(adapter);

        // Search functionality
        findViewById(R.id.search_view).addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filter(s.toString());
            }
            @Override public void afterTextChanged(Editable s) {}
        });

        // In ManageMenuActivity
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddEditMenuItemActivity.class);
            startActivityForResult(intent, 100);
        });

        // In MenuItemAdapter onBindViewHolder
        holder.editButton.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), AddEditMenuItemActivity.class);
            intent.putExtra("menu_item", item);
            ((AppCompatActivity) v.getContext()).startActivityForResult(intent, 101);
        });

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}