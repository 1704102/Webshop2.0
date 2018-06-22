package com.example.jersey.resource;

import com.example.jersey.controller.Controller;
import com.example.jersey.database.OrderDatabase;
import org.json.JSONObject;

import javax.ws.rs.*;

@Path("/order")
public class Order {

    @PUT
    @Consumes("application/json")
    public void addOrder(String x){
        JSONObject object = new JSONObject(x);
        OrderDatabase database = new OrderDatabase();
        database.addOrder(object);
    }

    @GET
    @Path("/id/{token}")
    @Consumes("application/json")
    public String getUser(@PathParam("token") String id) {
        OrderDatabase database = new OrderDatabase();
        return database.getOrder(Integer.parseInt(id)).toString();
    }


}
