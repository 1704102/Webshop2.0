package com.example.jersey.resource;
import com.example.jersey.controller.Controller;
import com.example.jersey.database.DatabaseHelper;
import com.example.jersey.database.LoginDatabase;
import com.example.jersey.model.User;
import org.json.JSONObject;

import javax.ws.rs.*;
import java.sql.PreparedStatement;

@Path("/login")
public class Login {

    public static Controller controller;

    public Login(){
        if (controller == null){
            controller = new Controller();
        }
    }

    @POST
    @Consumes("application/json")
    public String create(String x) {

        LoginDatabase database = new LoginDatabase();
        User user = database.login(new JSONObject(x));
        if (user != null){
            Controller.addUser(user);
            return user.getToken();
        }else {
            return "not valid credentials";
        }

    }

    @GET
    @Path("/id/{token}")
    @Consumes("application/json")
    public String getUser(@PathParam("token") String id) {
        return new JSONObject(Controller.getUser(Integer.parseInt(id))).toString();
    }
}