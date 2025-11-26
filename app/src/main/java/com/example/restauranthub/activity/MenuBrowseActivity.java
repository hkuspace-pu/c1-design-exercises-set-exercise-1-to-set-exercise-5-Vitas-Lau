package com.example.restauranthub.activity;

import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.restauranthub.R;
import com.example.restauranthub.adapter.GuestMenuAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MenuBrowseActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private RecyclerView rvMenuItems;
    private GuestMenuAdapter adapter;
    private List<MenuItem> allMenuItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_browse);

        btnBack = findViewById(R.id.btnBack);
        rvMenuItems = findViewById(R.id.rvMenuItems);

        rvMenuItems.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new GuestMenuAdapter();
        rvMenuItems.setAdapter(adapter);

        // Dummy Data
        allMenuItems.add(new MenuItem("Classic Burger", 12.99, "A juicy beef patty with fresh vegetables.", "Main Course", false, "https://www.allrecipes.com/thmb/5JVfA7MxfTUPfRerQMdF-cAclxg=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/25473-the-perfect-basic-burger-ddmfs-4x3-56eaba3833fd4a26a82755bcd0be0c54.jpg"));
        allMenuItems.add(new MenuItem("Margherita Pizza", 15.50, "Classic pizza with tomato, mozzarella, and basil.", "Main Course", true, "https://static.toiimg.com/thumb/56868564.cms?width=1200&height=900"));

        adapter.submitList(allMenuItems);

        // Preload images
        for (MenuItem item : allMenuItems) {
            Glide.with(this).load(item.imageUrl).preload();
        }

        btnBack.setOnClickListener(v -> finish());
    }

    public static class MenuItem {
        public String name;
        public double price;
        public String description;
        public String category;
        public boolean isVegetarian;
        public String imageUrl;

        public MenuItem(String name, double price, String description, String category, boolean isVegetarian, String imageUrl) {
            this.name = name;
            this.price = price;
            this.description = description;
            this.category = category;
            this.isVegetarian = isVegetarian;
            this.imageUrl = imageUrl;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MenuItem menuItem = (MenuItem) o;
            return Double.compare(menuItem.price, price) == 0 &&
                    isVegetarian == menuItem.isVegetarian &&
                    name.equals(menuItem.name) &&
                    Objects.equals(description, menuItem.description) &&
                    Objects.equals(category, menuItem.category) &&
                    Objects.equals(imageUrl, menuItem.imageUrl);
        }

    }
}