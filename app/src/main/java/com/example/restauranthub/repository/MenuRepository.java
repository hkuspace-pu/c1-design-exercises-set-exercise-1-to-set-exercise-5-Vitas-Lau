package com.example.restauranthub.repository;

import com.example.restauranthub.R;
import com.example.restauranthub.model.MenuItem;
import java.util.ArrayList;
import java.util.List;

public class MenuRepository {
    private static volatile MenuRepository instance;
    private final List<MenuItem> menuItems = new ArrayList<>();

    private MenuRepository() {
        // Private constructor to prevent instantiation
        addDummyData();
    }

    public static MenuRepository getInstance() {
        if (instance == null) {
            synchronized (MenuRepository.class) {
                if (instance == null) {
                    instance = new MenuRepository();
                }
            }
        }
        return instance;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void addMenuItem(MenuItem item) {
        menuItems.add(item);
    }

    public void removeMenuItem(MenuItem item) {
        menuItems.remove(item);
    }

    private void addDummyData() {
        menuItems.add(new MenuItem("Garlic Bread", 6.99, R.drawable.garlic_bread, true, "Starters",
                "Freshly baked artisan bread brushed with garlic-infused olive oil and topped with Italian herbs. Served warm with a side of marinara sauce for dipping.\n\nPerfect for sharing or as an appetizer before your main course.", true));
        menuItems.add(new MenuItem("Spring Rolls", 8.99, R.drawable.spring_rolls, true, "Starters",
                "Crispy vegetable spring rolls served with sweet chili dipping sauce.", true));
        menuItems.add(new MenuItem("Bruschetta", 7.99, R.drawable.bruschetta, true, "Starters",
                "Toasted bread topped with fresh tomatoes, basil, garlic, and olive oil.", true));
        menuItems.add(new MenuItem("Caesar Salad", 9.49, R.drawable.caesar_salad, true, "Starters",
                "Romaine lettuce with Caesar dressing, croutons, and parmesan cheese.", true));
        menuItems.add(new MenuItem("Cheese Nachos", 10.99, R.drawable.cheese_nachos, false, "Starters",
                "Tortilla chips topped with melted cheese, jalapeños, and sour cream.", true));

        menuItems.add(new MenuItem("Spaghetti Carbonara", 18.99, R.drawable.spaghetti_carbonara, true, "Mains",
                "Classic Italian pasta with eggs, cheese, pancetta, and black pepper.", false));
        menuItems.add(new MenuItem("Grilled Ribeye Steak", 32.99, R.drawable.grilled_ribeye_steak, false, "Mains",
                "Premium ribeye steak grilled to perfection, served with vegetables and potatoes.", false));
        menuItems.add(new MenuItem("Grilled Salmon", 28.99, R.drawable.grilled_salmon, false, "Mains",
                "Fresh Atlantic salmon grilled with lemon butter sauce and seasonal vegetables.", false));
        menuItems.add(new MenuItem("Vegetable Stir Fry", 15.49, R.drawable.vegetable_stir_fry, true, "Mains",
                "Mixed vegetables stir-fried in garlic soy sauce, served with rice.", true));
        menuItems.add(new MenuItem("Chicken Curry", 17.99, R.drawable.chicken_curry, true, "Mains",
                "Tender chicken in fragrant curry sauce with rice and naan.", false));
        menuItems.add(new MenuItem("Beef Burger", 14.99, R.drawable.beef_burger, true, "Mains",
                "Juicy beef patty with lettuce, tomato, onion, and special sauce.", false));

        menuItems.add(new MenuItem("Chocolate Lava Cake", 7.99, R.drawable.chocolate_lava_cake, true, "Desserts",
                "Warm chocolate cake with molten center, served with vanilla ice cream.", true));
        menuItems.add(new MenuItem("Tiramisu", 8.49, R.drawable.tiramisu, true, "Desserts",
                "Classic Italian dessert with coffee-soaked ladyfingers and mascarpone.", true));
        menuItems.add(new MenuItem("Cheesecake", 6.99, R.drawable.cheesecake, false, "Desserts",
                "Creamy New York style cheesecake with berry compote.", true));
        menuItems.add(new MenuItem("Ice Cream Sundae", 5.99, R.drawable.ice_cream_sundae, true, "Desserts",
                "Vanilla ice cream with chocolate sauce, whipped cream, and cherry.", true));

        menuItems.add(new MenuItem("Coca Cola", 2.99, R.drawable.coca_cola, true, "Drinks",
                "Chilled Coca Cola.", true));
        menuItems.add(new MenuItem("Lemonade", 3.49, R.drawable.lemonade, true, "Drinks",
                "Fresh homemade lemonade.", true));
        menuItems.add(new MenuItem("Red Wine", 25.99, R.drawable.red_wine, false, "Drinks",
                "House red wine.", true));
        menuItems.add(new MenuItem("Espresso", 4.99, R.drawable.espresso, true, "Drinks",
                "Strong Italian espresso.", true));
        menuItems.add(new MenuItem("Green Tea", 3.99, R.drawable.green_tea, true, "Drinks",
                "Premium green tea.", true));
    }
}