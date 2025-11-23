package com.example.restauranthub.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.restauranthub.R;
import com.google.android.material.chip.Chip;

public class MenuItemDetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView ivHeroImage;
    private TextView tvDishName, tvPrice, tvDescription;
    private Chip chipVegetarian, chipCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item_detail);

        toolbar = findViewById(R.id.toolbar);
        ivHeroImage = findViewById(R.id.ivHeroImage);
        tvDishName = findViewById(R.id.tvDishName);
        tvPrice = findViewById(R.id.tvPrice);
        tvDescription = findViewById(R.id.tvDescription);
        chipVegetarian = findViewById(R.id.chipVegetarian);
        chipCategory = findViewById(R.id.chipCategory);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // Get data from intent
        String name = getIntent().getStringExtra("name");
        double price = getIntent().getDoubleExtra("price", 0.0);
        int imageRes = getIntent().getIntExtra("imageRes", R.drawable.placeholder_food);
        String description = getIntent().getStringExtra("description");
        String category = getIntent().getStringExtra("category");
        boolean vegetarian = getIntent().getBooleanExtra("vegetarian", false);

        toolbar.setTitle(name);

        ivHeroImage.setImageResource(imageRes);
        tvDishName.setText(name);
        tvPrice.setText("$" + String.format("%.2f", price));
        tvDescription.setText(description != null ? description : "No description available.");
        chipCategory.setText(category != null ? category : "Unknown");
        chipVegetarian.setVisibility(vegetarian ? View.VISIBLE : View.GONE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}