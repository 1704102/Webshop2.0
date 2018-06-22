package com.example.jersey.database;

import com.example.jersey.controller.Controller;
import com.mysql.cj.util.EscapeTokenizer;
import com.mysql.cj.xdevapi.JsonArray;
import org.json.JSONArray;
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

    public JSONObject getOrder(int i) {
        JSONObject order = new JSONObject();
        connect();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from `order` a left join klant b on a.klant_id = b.id left join adres c on a.adres_id = c.id where a.id = ?");
            statement.setInt(1, i);
            ResultSet set = statement.executeQuery();
            while (set.next()){
                order.put("id", set.getInt("a.id"));
                JSONObject adres = new JSONObject();
                adres.put("city", set.getString("c.city"));
                adres.put("street", set.getString("c.adres"));
                adres.put("zipCode", set.getString("c.postcode"));
                order.put("adres", adres);
                JSONObject klant = new JSONObject();
                klant.put("name", set.getString("b.naam"));
                order.put("klant", klant);
            }

            statement = connection.prepareStatement("select * from orderregel a left join product b on a.product_id = b.id where a.order_id = ?");
            statement.setInt(1, i);
            set = statement.executeQuery();
            JSONArray orderLines = new JSONArray();
            while (set.next()){
              JSONObject line = new JSONObject();
              line.put("id", set.getInt("a.id"));
                line.put("productName", set.getString("b.naam"));
                line.put("amount", set.getInt("a.aantal"));
                orderLines.put(line);

            }
            order.put("orderLines", orderLines);
        }catch (Exception e){
            e.printStackTrace();
        }
        disconnect();


        return order;
    }
}
