package com.example.jersey.database;

import com.example.jersey.controller.Controller;
import com.example.jersey.model.Aanbieding;
import com.example.jersey.model.Category;
import com.example.jersey.model.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

public class ProductDatabase extends DatabaseHelper{

    public ArrayList<Category> getCategories(){
        ArrayList<Category> categories = new ArrayList<>();
        connect();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from categorie");
            ResultSet s = statement.executeQuery();
            while (s.next()){
                categories.add(new Category(s.getString("naam")));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        disconnect();
        return categories;
    }

    public void linkProducts(){
        connect();
        try {
            PreparedStatement statement = connection.prepareStatement("select a.naam naam, c.id id from categorie a left join categorie_has_product b on a.id = b.categorie_id left join product c on b.product_id = c.id where c.id != 0");
            ResultSet s = statement.executeQuery();
            while (s.next()){
                Category category = Controller.getCategory(s.getString("naam"));
                Product product = Controller.getProduct(s.getInt("id"));
                category.addProduct(product);
                product.addCategory(category);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        disconnect();
    }

    public ArrayList<Product> getProducts(){
        ArrayList<Product> products = new ArrayList<>();
        connect();
        try{
            PreparedStatement statement = connection.prepareStatement("select * from product");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("naam");
                float price = resultSet.getFloat("prijs");
                String picture = resultSet.getString("plaatje");
                String description = resultSet.getString("omschrijving");
                products.add(new Product(id,name,price,picture,description));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        disconnect();
        return products;
    }

    public void getAanbiedingen(){
        connect();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from aanbieding");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Product product = Controller.getProduct(resultSet.getInt("product_id"));
                Date begin = resultSet.getDate("dateBegin");
                Date end = resultSet.getDate("dateEnd");
                float price = resultSet.getFloat("prijs");
                String text =resultSet.getString("tekst");
                product.setAanbieding(new Aanbieding(begin,end,price,text));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        disconnect();
    }

    public int addProduct(Product product){
        int id = 0;
        connect();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into product (naam, plaatje, prijs, omschrijving) values (? ,?, ?, ?)");
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getPicture());
            preparedStatement.setFloat(3, product.getPrice());
            preparedStatement.setString(4, product.getDescription());
            preparedStatement.execute();

            preparedStatement = connection.prepareStatement("select max(id) as id from product");
            ResultSet s = preparedStatement.executeQuery();
            while (s.next()){
                id = s.getInt("id");
            }

            preparedStatement = connection.prepareStatement("insert into categorie_has_product (categorie_id, product_id) values (?,?)");
            preparedStatement.setInt(1,6);
            preparedStatement.setInt(2,id);
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
        disconnect();
        return id;
    }


}
