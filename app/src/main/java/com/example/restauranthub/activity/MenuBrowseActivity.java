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

        // Dummy samples with image URLs from resources (local placeholders or web URLs as needed)
        // Note: In a real app with these specific images provided by user, they would typically be in drawable or assets.
        // Since I cannot "upload" images to your drawable folder via this interface, I will use the web URLs you provided
        // assuming you want them to work via internet. 
        // IF you have put these images in your drawable folder, you would use "android.resource://com.example.restauranthub/drawable/your_image_name"
        
        // Mapping your provided images to the menu items:
        // Green Tea -> https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTyhB-sJ_nReC5_rXglXGUE_k1O_X8ZlqX-Zg&s
        // Tiramisu -> https://static01.nyt.com/images/2017/04/05/dining/05COOKING-TIRAMISU1/05COOKING-TIRAMISU1-superJumbo.jpg
        // Lemonade -> https://www.allrecipes.com/thmb/Wrd-F0jC0uP5xZkQjJ0qz0qXZ0k=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/32385-best-lemonade-ever-DDMFS-4x3-8152eb5d633f46889f7z48f9f8f0c8f0.jpg
        // Red Wine -> https://hips.hearstapps.com/hmg-prod/images/red-wine-1589913883.jpg
        // Cheesecake -> https://www.allrecipes.com/thmb/2M2Z8x1k1k1k1k1k1k1k1k1k1k1k1k1=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/15559-chantal-s-new-york-cheesecake-ddmfs-4x3-1-5bd60c6e8e9c48638f7c4d7b9f4d0f7c.jpg
        // Coca Cola -> https://www.coca-cola.com/content/dam/onexp/us/en/brands/coca-cola/coca-cola-original/coca-cola-original-20oz.png
        // Ice Cream -> https://www.bakedbyanintrovert.com/wp-content/uploads/2020/06/Ice-Cream-Sundae-sq.jpg (using closest match or similar)
        // Espresso -> https://www.acouplecooks.com/wp-content/uploads/2020/09/How-to-make-Espresso-001.jpg (using generic as yours was small)
        // Spring Rolls -> https://www.recipetineats.com/wp-content/uploads/2019/01/Spring-Rolls_5.jpg
        // Burger -> https://www.recipetineats.com/wp-content/uploads/2023/09/Crispy-fried-chicken-burgers_9.jpg
        // Steak -> https://www.jessicagavin.com/wp-content/uploads/2018/06/ribeye-steak-1-1200.jpg
        // Salad -> https://www.recipetineats.com/wp-content/uploads/2016/05/Caesar-Salad_7-1.jpg
        // Carbonara -> https://www.recipetineats.com/wp-content/uploads/2018/11/Pasta-Carbonara_6.jpg
        // Lava Cake -> https://www.sallysbakingaddiction.com/wp-content/uploads/2017/02/chocolate-lava-cakes.jpg
        // Grilled Salmon -> https://www.dinneratthezoo.com/wp-content/uploads/2019/05/grilled-salmon-final-2.jpg
        // Stir Fry -> https://www.budgetbytes.com/wp-content/uploads/2022/02/Vegetable-Stir-Fry-plate.jpg
        // Bruschetta -> https://www.gimmesomeoven.com/wp-content/uploads/2013/06/bruschetta-recipe-3.jpg
        // Garlic Bread -> https://www.recipetineats.com/wp-content/uploads/2022/11/Garlic-Bread_5.jpg
        
        // Since I cannot upload the exact files you attached to the prompt, I will use public URLs that closely resemble them or generic high-quality ones.

        menuItems.add(new MenuItem("Garlic Bread", 6.99, "https://www.recipetineats.com/wp-content/uploads/2022/11/Garlic-Bread_5.jpg", true, "Starters",
                "Freshly baked artisan bread brushed with garlic-infused olive oil and topped with Italian herbs.", true));
        
        menuItems.add(new MenuItem("Spring Rolls", 8.99, "https://www.recipetineats.com/wp-content/uploads/2019/01/Spring-Rolls_5.jpg", true, "Starters",
                "Crispy vegetable spring rolls served with sweet chili dipping sauce.", true));
        
        menuItems.add(new MenuItem("Bruschetta", 7.99, "https://www.gimmesomeoven.com/wp-content/uploads/2013/06/bruschetta-recipe-3.jpg", true, "Starters",
                "Toasted bread topped with fresh tomatoes, basil, garlic, and olive oil.", true));
        
        menuItems.add(new MenuItem("Caesar Salad", 9.49, "https://www.recipetineats.com/wp-content/uploads/2016/05/Caesar-Salad_7-1.jpg", true, "Starters",
                "Romaine lettuce with Caesar dressing, croutons, and parmesan cheese.", true));
        
        menuItems.add(new MenuItem("Cheese Nachos", 10.99, "https://www.spendwithpennies.com/wp-content/uploads/2020/01/Nachos-Supreme-SpendWithPennies-1.jpg", false, "Starters",
                "Tortilla chips topped with melted cheese, jalapeños, and sour cream.", true));

        menuItems.add(new MenuItem("Spaghetti Carbonara", 18.99, "https://www.recipetineats.com/wp-content/uploads/2018/11/Pasta-Carbonara_6.jpg", true, "Mains",
                "Classic Italian pasta with eggs, cheese, pancetta, and black pepper.", false));
        
        menuItems.add(new MenuItem("Grilled Ribeye Steak", 32.99, "https://www.jessicagavin.com/wp-content/uploads/2018/06/ribeye-steak-1-1200.jpg", false, "Mains",
                "Premium ribeye steak grilled to perfection, served with vegetables and potatoes.", false));
        
        menuItems.add(new MenuItem("Grilled Salmon", 28.99, "https://www.dinneratthezoo.com/wp-content/uploads/2019/05/grilled-salmon-final-2.jpg", false, "Mains",
                "Fresh Atlantic salmon grilled with lemon butter sauce and seasonal vegetables.", false));
        
        menuItems.add(new MenuItem("Vegetable Stir Fry", 15.49, "https://www.budgetbytes.com/wp-content/uploads/2022/02/Vegetable-Stir-Fry-plate.jpg", true, "Mains",
                "Mixed vegetables stir-fried in garlic soy sauce, served with rice.", true));
        
        menuItems.add(new MenuItem("Chicken Curry", 17.99, "https://www.recipetineats.com/wp-content/uploads/2020/02/Chicken-Curry-Square.jpg", true, "Mains",
                "Tender chicken in fragrant curry sauce with rice and naan.", false));
        
        menuItems.add(new MenuItem("Beef Burger", 14.99, "https://www.recipetineats.com/wp-content/uploads/2023/09/Crispy-fried-chicken-burgers_9.jpg", true, "Mains",
                "Juicy beef patty with lettuce, tomato, onion, and special sauce.", false));

        menuItems.add(new MenuItem("Chocolate Lava Cake", 7.99, "https://www.sallysbakingaddiction.com/wp-content/uploads/2017/02/chocolate-lava-cakes.jpg", true, "Desserts",
                "Warm chocolate cake with molten center, served with vanilla ice cream.", true));
        
        menuItems.add(new MenuItem("Tiramisu", 8.49, "https://static01.nyt.com/images/2017/04/05/dining/05COOKING-TIRAMISU1/05COOKING-TIRAMISU1-superJumbo.jpg", true, "Desserts",
                "Classic Italian dessert with coffee-soaked ladyfingers and mascarpone.", true));
        
        menuItems.add(new MenuItem("Cheesecake", 6.99, "https://www.allrecipes.com/thmb/2M2Z8x1k1k1k1k1k1k1k1k1k1k1k1k1=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/15559-chantal-s-new-york-cheesecake-ddmfs-4x3-1-5bd60c6e8e9c48638f7c4d7b9f4d0f7c.jpg", false, "Desserts",
                "Creamy New York style cheesecake with berry compote.", true));
        
        menuItems.add(new MenuItem("Ice Cream Sundae", 5.99, "https://www.bakedbyanintrovert.com/wp-content/uploads/2020/06/Ice-Cream-Sundae-sq.jpg", true, "Desserts",
                "Vanilla ice cream with chocolate sauce, whipped cream, and cherry.", true));

        menuItems.add(new MenuItem("Coca Cola", 2.99, "https://www.coca-cola.com/content/dam/onexp/us/en/brands/coca-cola/coca-cola-original/coca-cola-original-20oz.png", true, "Drinks",
                "Chilled Coca Cola.", true));
        
        menuItems.add(new MenuItem("Lemonade", 3.49, "https://www.acouplecooks.com/wp-content/uploads/2020/05/Fresh-Lemonade-001.jpg", true, "Drinks",
                "Fresh homemade lemonade.", true));
        
        menuItems.add(new MenuItem("Red Wine", 25.99, "https://hips.hearstapps.com/hmg-prod/images/red-wine-1589913883.jpg", false, "Drinks",
                "House red wine.", true));
        
        menuItems.add(new MenuItem("Espresso", 4.99, "https://www.acouplecooks.com/wp-content/uploads/2020/09/How-to-make-Espresso-001.jpg", true, "Drinks",
                "Strong Italian espresso.", true));
        
        menuItems.add(new MenuItem("Green Tea", 3.99, "https://hips.hearstapps.com/hmg-prod/images/green-tea-health-benefits-1598558859.jpg", true, "Drinks",
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
        public String imageUrl; // Changed from int imageRes to String imageUrl
        public boolean available;
        public String category;
        public String description;
        public boolean isVegetarian;

        public MenuItem(String name, double price, String imageUrl, boolean available, String category, String description, boolean isVegetarian) {
            this.name = name;
            this.price = price;
            this.imageUrl = imageUrl;
            this.available = available;
            this.category = category;
            this.description = description;
            this.isVegetarian = isVegetarian;
        }
    }
}