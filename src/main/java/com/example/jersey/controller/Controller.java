package com.example.jersey.controller;

import com.example.jersey.database.ProductDatabase;
import com.example.jersey.model.Category;
import com.example.jersey.model.Product;
import com.example.jersey.model.User;
import org.json.JSONObject;

import java.util.ArrayList;

public class Controller {

    private static ArrayList<User> users;
    private static ArrayList<Product> products;
    private static ArrayList<Category> categories;

    public Controller(){

        ProductDatabase database = new ProductDatabase();

        users = new ArrayList<>();
        products = database.getProducts();
        categories = database.getCategories();
        database.linkProducts();
        System.out.println(categories);


        database.getAanbiedingen();
    }

    public static void addUser(User user){
        users.add(user);
    }

    public static void addProduct(Product product){
        products.add(product);
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static ArrayList<Product> getProducts() {
        return products;
    }

    public static ArrayList<Product> getAanbiedingen(){
        ArrayList<Product> output = new ArrayList<>();
        products.forEach(product -> {
            if (product.getAanbieding() != null){
                output.add(product);
            }
        });
        return output;
    }

    public static Product getProduct(int id){
       for (Product product: products){
           if (product.getId() == id) return product;
       }
       return null;
    }

    public static Category getCategory(String name){
        for (Category category: categories){
            if (category.getName().equals(name)) return category;
        }
        return null;
    }

    public static int getUser(String token){
        for (User user: users){
            if (user.getToken().equals(token)) return user.getId();
        }
        return 0;
    }

    public static User getUser(int id){
        for (User user: users){
            if (user.getId() == id) return user;
        }
        return null;
    }

    public static void addProduct(JSONObject input){
        String name = input.getString("name");
        String picture = input.getString("picture");
        float price = input.getFloat("price");
        String description = input.getString("description");

        Product product = new Product(name,price, picture,description);
        ProductDatabase database = new ProductDatabase();
        product.setId(database.addProduct(product));
        product.addCategory(getCategory("nieuw"));
        products.add(product);
    }

    public static ArrayList<Product> getProductInCategory(String name) {
        ArrayList<Product> output= new ArrayList<>();
        for (Product product : products) {
           for (Category category: product.getCategories()){
               if (category.getName().equals(name))output.add(product);
           }
        }
        return output;
    }
}
