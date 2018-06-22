package com.example.jersey.resource;
import com.example.jersey.controller.Controller;
import com.example.jersey.database.DatabaseHelper;
import com.example.jersey.database.LoginDatabase;
import com.example.jersey.model.User;
import com.sun.jersey.api.NotFoundException;
import netscape.security.ForbiddenTargetException;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class Login {

    public static Controller controller;

    public Login(){
        if (controller == null){
            controller = new Controller();
        }
    }

    @POST
    @Produces({ MediaType.APPLICATION_JSON })
    public Response create(String x){

        LoginDatabase database = new LoginDatabase();
        User user = database.login(new JSONObject(x));
        if (user != null){
            Controller.addUser(user);
            JSONObject object = new JSONObject();
            object.put("token", user.getToken());
            return Response.ok(object.toString()).build();
        }else {
            return javax.ws.rs.core.Response.status(404).build();
        }

    }

    @GET
    @Path("/user/{id}")
    @Consumes("application/json")
    public Response getUser(@PathParam("id") int id) {
        User user = Controller.getUser(id);
        if (user == null){
            return javax.ws.rs.core.Response.status(403).build();
        }else {
            return Response.ok(new JSONObject(user).toString()).build();
        }

    }
}