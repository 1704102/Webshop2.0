package com.example.jersey.model;

import java.util.ArrayList;

public class Product {

    private int id;
    private String name;
    private float price;
    private String picture;
    private String description;
    private Aanbieding aanbieding;
    private ArrayList<Category> categories;

    public Product(int id, String name, float price, String picture, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.picture = picture;
        this.description = description;
        this.categories = new ArrayList<>();
    }

    public Product( String name, float price, String picture, String description) {
        this.name = name;
        this.price = price;
        this.picture = picture;
        this.description = description;
        this.categories = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getPicture() {
        return picture;
    }

    public String getDescription() {
        return description;
    }

    public Aanbieding getAanbieding() {
        return aanbieding;
    }

    public void setAanbieding(Aanbieding aanbieding) {
        this.aanbieding = aanbieding;
    }
    public void addCategory(Category category){
        categories.add(category);
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }
}
