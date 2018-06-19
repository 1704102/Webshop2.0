package com.example.jersey.database;

import com.example.jersey.controller.Controller;
import com.mysql.cj.xdevapi.JsonArray;
import org.json.JSONObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OrderDatabase extends DatabaseHelper{

    public void addOrder(JSONObject object){
        connect();
        int addressId = addAddress(object);
        int orderId = 0;

        try {
            PreparedStatement statement = connection.prepareStatement("insert into `order` (klant_id, adres_id) values (?,?)");
            statement.setInt(1, Controller.getUser(object.getString("token")));
            statement.setInt(2, addressId);
            statement.execute();

            statement = connection.prepareStatement("select max(id) as id from `order`");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                orderId = resultSet.getInt("id");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        for (Object orderLine: object.getJSONArray("shoppingCart")){
            addOrderLine((JSONObject) orderLine, orderId);
        }

        disconnect();
    }

    public int addAddress(JSONObject object){
        int id = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("insert into adres (postcode, adres, city) values (?, ?, ?) ");
            statement.setString(1, object.getString("zipCode"));
            statement.setString(2, object.getString("street"));
            statement.setString(3, object.getString("city"));
            statement.execute();

            statement = connection.prepareStatement("select max(id) as id from `adres`");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                id = resultSet.getInt("id");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return id;
    }

    public void addOrderLine(JSONObject object, int id){
        try {
            PreparedStatement statement = connection.prepareStatement("insert into orderregel (aantal, order_id, product_id) values (?, ?, ?) ");
            statement.setInt(1, object.getInt("amount"));
            statement.setInt(2, id);
            statement.setInt(3, object.getInt("id"));
            statement.execute();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
