package com.example.jersey.resource;
import com.example.jersey.controller.Controller;
import com.example.jersey.database.LoginDatabase;
import com.example.jersey.model.User;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;
public class LoginTest {

    @Test
    public void getUser() {
        int id = 1;
        JSONObject object = new JSONObject("{\"name\":\"Martijn\",\"id\":1,\"token\":\"E7EC2MRE8EOQG2UI3O\"}");
        Controller controller = new Controller();
        LoginDatabase database = new LoginDatabase();
        User user = database.login(new JSONObject("{'username': 'martijn', 'password': '0000'}"));
        Controller.addUser(user);
        Assert.assertEquals(object.getString("name"),new JSONObject(Controller.getUser(id)).getString("name"));
    }
}
