package com.example.jersey.database;

import com.example.jersey.model.User;
import org.json.JSONObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDatabase extends DatabaseHelper{

    public User login(JSONObject input){
        User user = null;
        String username = input.getString("username");
        String password = input.getString("password");

        connect();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from klant where username = ? and password = ?");
            statement.setString(1,username);
            statement.setString(2,password);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("naam");
                user =  new User(id,name);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        disconnect();
        return user;
    }

}
