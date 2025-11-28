package com.example.restauranthub.model;

public class MenuItem {
    public String name;
    public double price;
    public int imageRes;
    public boolean available;
    public String category;
    public String description;
    public boolean isVegetarian;

    public MenuItem(String name, double price, int imageRes, boolean available, String category, String description, boolean isVegetarian) {
        this.name = name;
        this.price = price;
        this.imageRes = imageRes;
        this.available = available;
        this.category = category;
        this.description = description;
        this.isVegetarian = isVegetarian;
    }
}