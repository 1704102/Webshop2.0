package com.example.jersey.model;

import java.util.ArrayList;

public class Category {
    String name;
    ArrayList<Product> products;

    public Category(String name) {
        this.name = name;
        products = new ArrayList<>();
    }

    public void addProduct(Product product){
        products.add(product);
    }

    public String getName() {
        return name;
    }
}
